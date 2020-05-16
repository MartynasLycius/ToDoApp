package com.mydomain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydomain.dao.TodoItemRepository;
import com.mydomain.models.TodoItem;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@Override
	public TodoItem getTodoItemById(Long id) {
		Optional<TodoItem> todo = todoItemRepository.findById(id);
        
        if(todo.isPresent()) {
            return todo.get();
        } else {
            throw new RuntimeException("No todo record exist for given id");
        }
	}

	@Override
	public List<TodoItem> getTodoItems(Long offset, Integer pageSize) {
		return todoItemRepository.findAll();
	}

	@Override
	public void saveTodoItem(TodoItem todoItem) {
		todoItemRepository.save(todoItem);
	}

	@Override
	public void deleteTodoItemById(TodoItem todoItem) {
		todoItemRepository.delete(todoItem);
	}

}
