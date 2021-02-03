package com.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/// History table entity is connected with HistoryRepository
@Entity
public class History {

    @Id
    @GeneratedValue
    private Integer id;
    /// table columns
    private String taskName;
    private String taskDesc;
    private Timestamp createDate,deleteDate;

    // Default constructor
    protected History() {
    }

    public History(String taskName, String taskDesc, Timestamp createDate,Timestamp deleteDate) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.createDate=createDate;
        this.deleteDate=deleteDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Timestamp deleteDate) {
        this.deleteDate = deleteDate;
    }
    public Integer getId() {
        return id;
    }

    public String gettaskName() {
        return taskName;
    }

    public void settaskName(String taskName) {
        this.taskName = taskName;
    }

    public String gettaskDesc() {
        return taskDesc;
    }

    public void settaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Override
    public String toString() {
        return String.format("Task[id=%d, taskName='%s', taskDesc='%s']", id,
                taskName, taskDesc);
    }

}
