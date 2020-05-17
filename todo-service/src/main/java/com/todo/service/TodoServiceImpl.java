package com.todo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.exception.ResourceNotFoundException;
import com.todo.persistence.model.Todo;
import com.todo.persistence.repository.TodoRepository;
import com.todo.utility.ApiUtils;

@Service
@Transactional
public class TodoServiceImpl implements TodoService{
	
	private TodoRepository todoRepository;
	
	private ApiUtils apiUtils;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository,ApiUtils apiUtils) {
		this.todoRepository = todoRepository;
		this.apiUtils = apiUtils;
	}

	@Override
	public ResponseEntity<Todo> createTodo(Todo todo,HttpServletRequest request) {
		
		Todo newTodo = todoRepository.save(todo);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", todoUrlHelper(newTodo, request));
        
        return new ResponseEntity<Todo>(newTodo, responseHeaders, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Todo> updateTodo(Long id,Todo updateTodo) {
		
		Todo existingTodo = findTodoIfExists(id);
	    apiUtils.merge(existingTodo, updateTodo);
	    existingTodo.setId(id);
	    
	    return new ResponseEntity<Todo>(todoRepository.save(existingTodo),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Todo> deleteTodo(Long id) {
		Todo existingTodo = findTodoIfExists(id);
		todoRepository.delete(existingTodo);
		
		return new ResponseEntity<Todo>(HttpStatus.NO_CONTENT);
	}

	@Override
	public List<Todo> getTodos() {
		return todoRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));
	}

	@Override
	public Todo findById(Long id) {
		return findTodoIfExists(id);
	}
	
	// Non API
	private Todo findTodoIfExists(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
    }
	
	private String todoUrlHelper(Todo todo, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(todo.getId());

        return resourcePath.toString();
    }


}
