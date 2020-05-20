package com.app.todo.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.repository.TodoRepository;
import com.app.todo.backend.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired TodoRepository todoRepo;

	@Override
	public void save(Todo todo) {
		todoRepo.save(todo);
	}

	@Override
	public List<Todo> getAll() {
		return todoRepo.findAll();
	}

	@Override
	public Todo getBy(int id) {
		return todoRepo.findById(id).get();
	}

	@Override
	public void delete(Todo todo) {
		todoRepo.delete(todo);
	}

}
