package com.todo.todo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.todo.model.Todo;
import com.todo.todo.repository.TodoJpaRepository;
import com.todo.todo.service.TodoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoContoroller {

	@Autowired
	private TodoJpaRepository todoJpaRepository;
	@Autowired
	private TodoService todoService;


	@GetMapping("/jpa/todos")
	public List<Todo> getAllTodos() {

		return todoService.getAllTodos();

	}

	@GetMapping("/jpa/todos/{id}")
	public Todo getTodo(@PathVariable long id) {
		return todoService.findTodo(id);


	}


	@DeleteMapping("/jpa/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable long id) {


		todoService.deleteTodo(id);
		return ResponseEntity.noContent().build();

	}

	// Edit/Update a Todo
	@PutMapping("/jpa/todos/{id}")
	public ResponseEntity<Todo> updateTodo(

			@PathVariable long id, @RequestBody Todo todo) {

		todoService.updateTodo(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@PostMapping("/jpa/todos")
	public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {

		Todo createdTodo = todoService.saveTodo(todo);
		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
