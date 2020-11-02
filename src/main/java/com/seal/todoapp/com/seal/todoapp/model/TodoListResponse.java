package com.seal.todoapp.com.seal.todoapp.model;

import com.seal.todoapp.com.seal.todoapp.entity.Todo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoListResponse {

    private Long id;

    private String title;

    private String description;

    private String complete;

    private String date;

    public TodoListResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.complete = todo.getComplete() ? "Completed" : "Not Completed";
        this.date = todo.getDate().toString();
    }
}
