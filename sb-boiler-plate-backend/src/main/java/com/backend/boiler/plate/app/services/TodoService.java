package com.backend.boiler.plate.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.boiler.plate.app.models.Todo;
import com.backend.boiler.plate.app.repositories.TodoEntityRepository;
import com.backend.boiler.plate.app.repositories.UserRepository;

@Service
public class TodoService {

	@Autowired
	private TodoEntityRepository todoEntityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Todo saveTodo(Todo todo, String username) {
		todo.setUser(userRepository.findOneByUsername(username).get());
		return todoEntityRepository.save(todo);
	}
	
	public List<Todo> getTodos(String username) {
		return userRepository.findOneByUsername(username).get().getTodos();
	}
	
	public Todo getTodo(Long id) {
		return todoEntityRepository.findById(id).get();
	}
	
	public void deleteTodo(Long id) {
		todoEntityRepository.deleteById(id);
	}
}
