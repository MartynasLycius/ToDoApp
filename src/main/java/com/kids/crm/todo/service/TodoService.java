package com.kids.crm.todo.service;

import com.kids.crm.todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> fetch();

    void create(Todo todo);
}
