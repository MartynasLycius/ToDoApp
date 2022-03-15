package com.todo.todo.service;

import java.util.List;

import com.todo.todo.model.Todo;

public interface TodoService {

	List<Todo> getAllTodos();

	Todo saveTodo(Todo todo);

	void updateTodo(Todo todo);

	Todo findTodo(long id);

	void deleteTodo(long id);

}
