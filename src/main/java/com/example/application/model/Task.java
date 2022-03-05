package com.example.application.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private LocalDate date;

    private String name;

    @Column(length = 2000)
    private String description;

    public Task() {
    }

    public Task(LocalDate date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
