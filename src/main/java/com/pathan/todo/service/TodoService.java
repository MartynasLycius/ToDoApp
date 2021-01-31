package com.pathan.todo.service;

import com.pathan.todo.model.Todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Pathan on 30-Jan-21.
 */
public interface TodoService {
    List<Todo> getTodosByUser(String user);

    Optional<Todo> getTodoById(long id);

    void saveTodo(Todo todo);

    void deleteTodo(long id);

    List<Todo> getTodos();


}
