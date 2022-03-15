package com.todo.todo.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo.model.Todo;
import com.todo.todo.repository.TodoJpaRepository;
import com.todo.todo.service.TodoService;

@Service
public class TodoServieImpl implements TodoService {
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	public List<Todo> getAllTodos() {
		return todoJpaRepository.findAll();
		

	}
	public 	Todo findTodo(long id) {
		return todoJpaRepository.findById(id);
	}
	public Todo saveTodo(Todo todo) {
		todoJpaRepository.save(todo);
		return todo;
	}
	
	public void updateTodo(Todo todo) {
		todoJpaRepository.save(todo);
		
	}
	public void deleteTodo(long id) {
		todoJpaRepository.deleteById(id);
	}

	
}
