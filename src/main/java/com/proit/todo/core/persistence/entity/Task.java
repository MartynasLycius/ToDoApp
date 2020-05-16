package com.proit.todo.core.persistence.entity;

import com.proit.todo.core.constant.Enums;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {
    private int id;
    private String name;
    private String description;
    private Enums.TASK_STATE state;
    private Date createdDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state", nullable = false)
    public Enums.TASK_STATE getState() {
        return state;
    }

    @Basic
    @Column(name = "created_date", nullable = false)
    public Date getCreatedDate() {
        return createdDate;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(Enums.TASK_STATE state) {
        this.state = state;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (state != task.state) return false;
        return createdDate != null ? createdDate.equals(task.createdDate) : task.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
