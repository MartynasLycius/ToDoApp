package com.interview.task.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.task.ToDoTrackerAppApplication;
import com.interview.task.models.TodoItem;
import com.interview.task.repositories.TodoRepository;

@Service
public class TodoServices {
	
	static final Logger logger = LoggerFactory.getLogger(ToDoTrackerAppApplication.class);
	
	@Autowired
	private TodoRepository todoRepo;
	
	public TodoItem addTodoItem(TodoItem todoItem) {
		TodoItem savedTodoItem = todoRepo.save(todoItem);
		return savedTodoItem;
	}
	
	public TodoItem updateTodoItem(TodoItem todoItem) {
		return todoRepo.save(todoItem);
	}
	
	public List<TodoItem> findCompleteTasks() {
		List<TodoItem> todoList = new ArrayList<>();
		todoRepo.findAllCompleteTodos().forEach(todoList::add);
		return todoList;
	}
	
	public List<TodoItem> findAllTasks() {
		List<TodoItem> todoList = new ArrayList<>();
		todoRepo.findAll().forEach(todoList::add);
		return todoList;
	}

	public void updateCompleteState(List<Long> taskIds) {
		todoRepo.updateCompleteState(taskIds);
	}
	
	public void deleteTodoItem(long taskId) {
		todoRepo.deleteById(taskId);
	}
	
	public void deleteListedTodoItems(List<Long> taskIds) {
		todoRepo.deleteTasksWithIds(taskIds);
	}
	
}
