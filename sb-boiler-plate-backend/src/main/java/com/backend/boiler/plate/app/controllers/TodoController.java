package com.backend.boiler.plate.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.boiler.plate.Utils.Mappings;
import com.backend.boiler.plate.app.models.Todo;
import com.backend.boiler.plate.app.services.TodoService;

@RestController
@RequestMapping(Mappings.API)
public class TodoController {

	@Autowired
	private TodoService todoService;

	@PostMapping(Mappings.API_TODOS_ADD)
	public Todo saveTodo(@RequestBody Todo todo, HttpServletRequest request) {
		return todoService.saveTodo(todo, request.getUserPrincipal().getName());
	}

	@GetMapping(Mappings.API_TODOS)
	public List<Todo> getTodos(HttpServletRequest request) {
		return todoService.getTodos(request.getUserPrincipal().getName());
	}

	@GetMapping(Mappings.API_TODOS_SINGLE)
	public Todo getTodo(@PathVariable long id, HttpServletRequest request) {
		return todoService.getTodo(id);
	}

	@PutMapping(Mappings.API_TODOS_SINGLE)
	public Todo updateTodo(@RequestBody Todo todo, HttpServletRequest request) {
		return todoService.saveTodo(todo, request.getUserPrincipal().getName());
	}

	@DeleteMapping(Mappings.API_TODOS_SINGLE)
	public String deleteTodo(@PathVariable long id) {
		todoService.deleteTodo(id);
		return "{}";
	}
}
