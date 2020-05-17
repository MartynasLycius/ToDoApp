package com.todo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.todo.persistence.model.Todo;

public interface TodoService {
	
	ResponseEntity<Todo> createTodo(Todo todo,HttpServletRequest request);
	
	ResponseEntity<Todo> updateTodo(Long id,Todo updateTodo);
	
	ResponseEntity<Todo> deleteTodo(Long Id);
	
	List<Todo> getTodos();

    Todo findById(Long id);

}
