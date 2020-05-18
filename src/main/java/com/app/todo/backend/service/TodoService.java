package com.app.todo.backend.service;

import java.util.List;

import com.app.todo.backend.entity.Todo;

public interface TodoService {
	public void save(Todo todo);
	public List<Todo> getAll();
	public Todo getBy(int id);
	public void delete(Todo todo);
	
}
