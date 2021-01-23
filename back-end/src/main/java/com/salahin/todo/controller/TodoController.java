package com.salahin.todo.controller;

import com.salahin.todo.core.ResponseObject;
import com.salahin.todo.model.TodoModel;
import com.salahin.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
//@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/todo",
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {
	
    private final TodoService todoService;
	
    @Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PostMapping("create")
    public ResponseObject createTodo(@RequestBody TodoModel todoModel){
        ResponseObject responseObject;
		responseObject = this.todoService.createTodo(todoModel);
        return responseObject;
    }
	
	@GetMapping("get")
	public ResponseObject getTodoById(@RequestBody UUID uuid){
		ResponseObject responseObject;
		responseObject = this.todoService.getTodoById(uuid);
		return responseObject;
	}
	
	@GetMapping("list")
	public ResponseObject getAllTodo(){
		ResponseObject responseObject;
		responseObject = this.todoService.getAllTodo();
		return responseObject;
	}
 
	@PutMapping("update")
	public ResponseObject updateTodo(@RequestBody TodoModel todoModel){
		ResponseObject responseObject;
		responseObject = this.todoService.updateTodo(todoModel);
		return responseObject;
	}
	
	@DeleteMapping("delete")
	public ResponseObject deleteTodo(@RequestBody UUID uuid){
		ResponseObject responseObject;
		responseObject = this.todoService.deleteTodoById(uuid);
		return responseObject;
	}
	
}
