package com.proit.todo.api.dto.task;

import com.proit.todo.core.constant.Enums;

public class TaskSummaryDto {
    private String name;
    private Enums.TASK_STATE state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enums.TASK_STATE getState() {
        return state;
    }

    public void setState(Enums.TASK_STATE state) {
        this.state = state;
    }
}
