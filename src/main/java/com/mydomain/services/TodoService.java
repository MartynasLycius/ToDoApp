package com.mydomain.services;

import java.util.List;

import com.mydomain.models.TodoItem;

public interface TodoService {
	public TodoItem getTodoItemById(Long id);
	public List<TodoItem> getTodoItems(Long offset, Integer pageSize);
	public void saveTodoItem(TodoItem todoItem);
	public void deleteTodoItemById(TodoItem todoItem);
}
