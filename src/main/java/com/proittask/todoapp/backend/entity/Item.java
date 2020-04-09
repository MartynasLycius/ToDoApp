package com.proittask.todoapp.backend.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Item extends AbstractEntity implements Cloneable{

    public enum Status {
        NotStarted, Started, Working, Pending, Completed, NotDone, NeedTime
    }
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private LocalDate targetDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Item.Status status;

    public Item() {
    }
    public Item(String name) {
        setName(name);
    }

    public Item(@NotNull String name, @NotNull String description, LocalDate targetDate, Item.Status status) {
        this.name = name;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
    }

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

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
