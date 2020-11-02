package com.seal.todoapp.com.seal.todoapp.service;

import com.seal.todoapp.com.seal.todoapp.entity.Todo;
import com.seal.todoapp.com.seal.todoapp.model.TodoRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TodoMapper {

    public static Todo todoRequestToTodo(TodoRequest res, Todo todo) {
        todo.setTitle(res.getTitle());
        todo.setDescription(res.getDescription());
        todo.setDate(res.getDate());
        todo.setComplete(res.getComplete());
        return todo;
    }

}
