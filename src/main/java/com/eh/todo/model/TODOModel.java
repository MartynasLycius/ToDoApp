package com.eh.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Md. Emran Hossain <emranhos1@gmail.com>
 * @version 1.0.00 EH
 * @since 1.0.00 EH
 */
@Entity
@Table(name = "todo_table")
public class TODOModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_table_id")
    private Long todoTableId;
    @Column(name = "task")
    private String task;
    @Column(name = "task_details")
    private String taskDetails;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "completed_date")
    private String completedDate;
    @Column(name = "is_done")
    private int isDone;

    public Long getTodoTableId() {
        return todoTableId;
    }

    public void setTodoTableId(Long todoTableId) {
        this.todoTableId = todoTableId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }
}