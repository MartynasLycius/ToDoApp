package com.rali.servicce;

import com.rali.entity.Todo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface TodoService {
    List<Todo> getTodoItems(Integer page, Integer size);

    Todo createTodoItem(Todo todo);

    Todo updateTodoItem(Todo todo) throws InvocationTargetException, IllegalAccessException;

    Todo getTodoItemById(String id);

    void deleteTodoItem(String id);
}
