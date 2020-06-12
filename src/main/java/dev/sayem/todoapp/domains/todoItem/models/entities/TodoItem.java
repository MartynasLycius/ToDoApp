package dev.sayem.todoapp.domains.todoItem.models.entities;

import dev.sayem.todoapp.domains.common.base.models.entities.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todo_items")
public class TodoItem extends BaseEntity {

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
