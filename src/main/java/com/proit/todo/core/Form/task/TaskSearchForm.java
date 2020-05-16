package com.proit.todo.core.Form.task;

import com.proit.todo.core.Form.PaginationForm;
import com.proit.todo.core.constant.Enums;

import java.util.Date;

public class TaskSearchForm extends TaskForm {
    private Enums.TASK_STATE state;
    private Date createdDate;
    private PaginationForm pagination;

    public TaskSearchForm() {
        this.pagination = new PaginationForm();
    }

    public PaginationForm getPagination() {
        return pagination;
    }

    public Enums.TASK_STATE getState() {
        return state;
    }

    public void setState(Enums.TASK_STATE state) {
        this.state = state;
    }

    public void setPagination(PaginationForm pagination) {
        this.pagination = pagination;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
