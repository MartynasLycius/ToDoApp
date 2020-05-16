package com.proit.todo.core.service.impl;

import com.proit.todo.core.persistence.specification.TaskSpecification;
import com.proit.todo.core.persistence.repository.TaskRepository;
import com.proit.todo.core.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import com.proit.todo.core.service.iface.TaskService;
import com.proit.todo.core.Form.task.TaskCreateForm;
import com.proit.todo.core.Form.task.TaskSearchForm;
import com.proit.todo.core.Form.task.TaskUpdateForm;
import com.proit.todo.core.persistence.entity.Task;
import com.proit.todo.core.helper.SanitizerHelper;
import com.proit.todo.core.Form.PaginationForm;
import org.springframework.stereotype.Service;
import com.proit.todo.core.helper.DateHelper;
import org.apache.commons.lang3.StringUtils;
import com.proit.todo.core.constant.Enums;
import org.springframework.data.domain.*;


import java.util.Date;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Task getById(int id, boolean throwExceptionIfNotFound) {
        Task task = this.taskRepository.getDistinctById(id);

        if(task==null && throwExceptionIfNotFound){
            throw new RecordNotFoundException(Task.class,"id",id);
        }
        return task;
    }

    @Override
    public Task getById(int id) {
        return getById(id,false);
    }

    @Override
    public Page<Task> getAllBySearchCriteria() {
        return this.getAllBySearchCriteria(new TaskSearchForm());
    }

    public List<Task> getAllBySearchCriteriaNoPagination(TaskSearchForm taskSearchForm) {


        /**
         * Criteria building
         * */
        Specification<Task> specification = Specification
                .where(TaskSpecification.getSearch(taskSearchForm));

        return  this.taskRepository.findAll(specification);
    }

    @Override
    public Page<Task> getAllBySearchCriteria(TaskSearchForm taskSearchForm) {


        /**
         * Criteria building
         * */
        Specification<Task> specification = Specification
                                            .where(TaskSpecification.getSearch(taskSearchForm));

        /**
         * Pagination data
         * */
        PaginationForm paginationForm = taskSearchForm.getPagination();
        int page =  paginationForm.getPageNumber();
        int limit = paginationForm.getPageSize();
        Pageable pageable = PageRequest.of(page, limit);


        return  this.taskRepository.findAll(specification,pageable);
    }


    @Override
    public Task create(TaskCreateForm form) {

        /**
         * Trimming and sanitizing
         * */
        SanitizerHelper.sanitizeAndTrimStringValueInFlatSingleForm(form);

        /**
         * Preparing values
         * */

        String name = form.getName();
        String description = StringUtils.isNoneBlank(form.getDescription())
                                    ?form.getDescription():null;

        Date currentDate = DateHelper.getCurrentDate();

        /**
         * Create task
         * */
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setState(Enums.TASK_STATE.NEW);
        task.setCreatedDate(currentDate);

        /**
         * Save task
         * */
        return this.save(task);
    }

    @Override
    public Task update(TaskUpdateForm form) {
        int id = form.getId();
        Task task = this.getById(id,true);

        /**
         * Trimming and sanitizing
         * */
        SanitizerHelper.sanitizeAndTrimStringValueInFlatSingleForm(form);

        /**
         * Preparing values
         * */
        String name = form.getName();
        String description = StringUtils.isNoneBlank(form.getDescription())
                                    ?form.getDescription():null;

        /**
         * Create task
         * */
        task.setName(name);
        task.setDescription(description);

        /**
         * Save task
         * */
        return this.save(task);
    }

    @Override
    public Task updateState(int id,Enums.TASK_STATE state) {
        if(state==null)return null;

        Task task = this.getById(id,true);
        if(task.getState().equals(state))return task;

        /**
         * Update state
         * */
        task.setState(state);

        /**
         * Save task
         * */
        return this.save(task);
    }



    private Task save(Task task){
        return this.taskRepository.save(task);
    }
}
