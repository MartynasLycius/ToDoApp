package com.proit.todo.api.dto.task;

import java.util.Date;

public class TaskDetailedDto extends TaskSummaryDto {
    private String description;
    private Date createdDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
