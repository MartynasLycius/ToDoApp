package com.todo.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.persistence.model.Todo;
import com.todo.service.TodoService;

@RestController
@RequestMapping("/api/v1")
public class TodoResource {

	@Autowired
	private TodoService todoService;
	
	// Get all todos
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        return new ResponseEntity<List<Todo>>(todoService.getTodos(), HttpStatus.OK);
    }
    
    // Get a single todo
    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id){
        return new ResponseEntity<Todo>(todoService.findById(id), HttpStatus.OK);
    }

    // Create a new todo
    @PostMapping("/todos")
    public ResponseEntity<Todo> createNewTodo(@Valid @RequestBody Todo todo, HttpServletRequest request){
        return todoService.createTodo(todo, request);
    }

    // Update todo
    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    // Delete todo
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }

	
}
