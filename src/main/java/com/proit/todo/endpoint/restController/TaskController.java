package com.proit.todo.endpoint.restController;

import com.proit.todo.core.Form.task.TaskCreateForm;
import com.proit.todo.core.Form.task.TaskSearchForm;
import com.proit.todo.core.Form.task.TaskUpdateForm;
import com.proit.todo.core.constant.Enums;
import com.proit.todo.core.exceptions.UnprocessedEntityException;
import com.proit.todo.core.persistence.entity.Task;
import com.proit.todo.core.service.iface.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/task", produces = { "application/json" })
public class TaskController {

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(path = "/get-by-id/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        Task task = this.taskService.getById(id,true);
        return ResponseEntity.ok(task);
    }

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody TaskCreateForm taskCreateForm,
                                    BindingResult result){

        /**
         * Form Validation Error Check
         * */
        if(result.hasErrors())throw new UnprocessedEntityException(result);

        /**
         * Creating Task
         * */
        Task task = this.taskService.create(taskCreateForm);

        return ResponseEntity.ok(task);
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> update(@Valid @RequestBody TaskUpdateForm taskUpdateForm,
                                    BindingResult result){
        /**
         * Form Validation Error Check
         * */
        if(result.hasErrors())throw new UnprocessedEntityException(result);


        /**
         * Updating Task
         * */
        Task task = this.taskService.update(taskUpdateForm);

        return ResponseEntity.ok(task);
    }

    @RequestMapping(path = "/mark-as-completed/{id}",method = RequestMethod.POST)
    public ResponseEntity<?> markAsCompleted(@PathVariable("id") int id){

        Task task = this.taskService.updateState(id, Enums.TASK_STATE.DONE);

        return ResponseEntity.ok(task);
    }

    @RequestMapping(path = "/mark-as-new/{id}",method = RequestMethod.POST)
    public ResponseEntity<?> markAsNew(@PathVariable("id") int id){

        Task task = this.taskService.updateState(id, Enums.TASK_STATE.NEW);

        return ResponseEntity.ok(task);
    }

    @RequestMapping(path = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<?> getAll(TaskSearchForm taskSearchForm){
        Page<Task> taskPage = this.taskService.searchTask(taskSearchForm);
        return ResponseEntity.ok(taskPage);
    }

}