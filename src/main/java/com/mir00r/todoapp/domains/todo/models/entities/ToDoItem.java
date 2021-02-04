package com.mir00r.todoapp.domains.todo.models.entities;

import com.mir00r.todoapp.domains.common.models.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
@Entity
@Table(name = "todo_items")
public class ToDoItem extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
