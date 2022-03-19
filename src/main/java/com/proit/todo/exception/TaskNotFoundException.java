package com.proit.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        this("Task not found!!");
    }

    public TaskNotFoundException(Long id) {
        this("Task not found with id: " + id);
    }

    private TaskNotFoundException(String message) {
        super(message);
    }

}
