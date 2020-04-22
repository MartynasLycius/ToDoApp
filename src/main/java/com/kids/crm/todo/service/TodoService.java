package com.kids.crm.todo.service;

import com.kids.crm.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findAll();

    Optional<Todo> findById(Long id);

    void create(Todo todo);

    void delete(Todo todo);

    void update(Todo todo);
}
