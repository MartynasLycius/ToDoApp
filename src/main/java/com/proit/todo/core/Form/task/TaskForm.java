package com.proit.todo.core.Form.task;

import java.util.Date;

public class TaskForm {
    private String name;
    private String description;


    public String getName() {
        return name;
    }

    public TaskForm setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskForm setDescription(String description) {
        this.description = description;
        return this;
    }
}
