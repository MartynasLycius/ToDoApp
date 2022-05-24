package com.todo.app.core.service;

import com.todo.app.core.entity.TodoItem;

import java.util.List;


public interface TodoItemService {

    TodoItem add(TodoItem item);

    List<TodoItem> getAll();

    TodoItem findById(long id);

    TodoItem update(TodoItem task);

    void deleteById(long id);
}
