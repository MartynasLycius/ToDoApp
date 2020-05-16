package com.proit.todo.core.validator;

import org.springframework.beans.factory.annotation.Autowired;
import com.proit.todo.core.service.iface.TaskService;
import com.proit.todo.core.Form.task.TaskCreateForm;
import com.proit.todo.core.Form.task.TaskUpdateForm;
import com.proit.todo.core.persistence.entity.Task;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * Validation concerning custom Business login
 * */
@Component
public class TaskValidator {

    private TaskService taskService;


    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void validateCreate(TaskCreateForm form, Errors errors){

    }

    public void validateUpdate(TaskUpdateForm form, Errors errors){
        this.validateTask(form.getId(),errors);
    }


    private void validateTask(int id,Errors errors){
        if(errors.hasFieldErrors("id"))return;

        Task task = this.taskService.getById(id);
        if(task==null){

            /**
             * Error with custom message for end user
             * */
            errors.rejectValue("id","recordNotFound","Task not found by id "+id);
        }
    }
}