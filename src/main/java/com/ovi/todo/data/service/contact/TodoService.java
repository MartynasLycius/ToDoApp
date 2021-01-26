package com.ovi.todo.data.service.contact;

import com.ovi.todo.data.dto.request.TodoRequest;
import com.ovi.todo.data.dto.response.TodoResponse;

import java.util.List;

public interface TodoService {

    List<TodoResponse> getTodoList();
    TodoResponse save(TodoRequest todo);
    TodoResponse update(String todoId, TodoRequest todo);
    void deleteTodoItem(String id);
    TodoResponse getTodoById(String id);

}
