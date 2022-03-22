// Please ignore this. Was created for rest endpoint. later opt vaadin as frontend.


package com.proit.todo.controller;

import com.proit.todo.model.Task;
import com.proit.todo.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public Page<Task> list(@PageableDefault Pageable pageable) {
        return taskService.findAll(pageable);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Task save(@RequestBody @Valid Task task) {
        return taskService.save(task);
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) {
        return taskService.find(id);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody @Valid Task task) {
        return taskService.update(id, task);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
*/
