package com.seal.todoapp.com.seal.todoapp.model;

import com.seal.todoapp.com.seal.todoapp.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoResponse {

    private Long id;

    private String title;

    private String description;

    private Boolean complete;

    private LocalDate date;

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.complete = todo.getComplete();
        this.date = todo.getDate();
    }
}
