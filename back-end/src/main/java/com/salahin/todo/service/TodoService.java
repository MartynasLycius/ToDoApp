package com.salahin.todo.service;

import com.salahin.todo.core.ResponseObject;
import com.salahin.todo.model.TodoModel;

import java.util.UUID;

public interface TodoService {
	ResponseObject createTodo(TodoModel todoModel);
	ResponseObject updateTodo(TodoModel todoModel);
	ResponseObject getTodoById(UUID uuid);
	ResponseObject getAllTodo();
	ResponseObject deleteTodoById(UUID uuid);
}
