package com.santam.todolycias.backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Todo {
    private int id;
    private String title;
    private String desc;
    private int status;
    private Timestamp deadline;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Users usersByOwnby;
    private String descc;
    private int statuss;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "descc")
    public String getDescc() {
        return descc;
    }

    public void setDescc(String descc) {
        this.descc = descc;
    }

    @Basic
    @Column(name = "statuss")
    public int getStatuss() {
        return statuss;
    }

    public void setStatuss(int statuss) {
        this.statuss = statuss;
    }

    @Basic
    @Column(name = "deadline")
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (id != todo.id) return false;
        if (status != todo.status) return false;
        if (title != null ? !title.equals(todo.title) : todo.title != null) return false;
        if (desc != null ? !desc.equals(todo.desc) : todo.desc != null) return false;
        if (deadline != null ? !deadline.equals(todo.deadline) : todo.deadline != null) return false;
        if (createdAt != null ? !createdAt.equals(todo.createdAt) : todo.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(todo.updatedAt) : todo.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ownby", referencedColumnName = "id", nullable = false)
    public Users getUsersByOwnby() {
        return usersByOwnby;
    }

    public void setUsersByOwnby(Users usersByOwnby) {
        this.usersByOwnby = usersByOwnby;
    }

}
