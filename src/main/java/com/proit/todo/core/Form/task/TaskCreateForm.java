package com.proit.todo.core.Form.task;

import javax.validation.constraints.NotBlank;

public class TaskCreateForm extends TaskForm{
    @NotBlank(message = "Name required")
    @Override
    public String getName() {
        return super.getName();
    }


}
