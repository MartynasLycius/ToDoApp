package com.mydomain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public List<TodoItem> getTodoItems(Integer page, Integer pageSize, String sortBy) {		
		Pageable pageable = PageRequest.of(page.intValue(), pageSize, Sort.by(sortBy));
		Page<TodoItem> todoPage = todoItemRepository.findAll(pageable);
		
		return todoPage.getContent();
	}
	
	@Override
	public PagedTodoItems getTodosPage(Integer page, Integer pageSize, String sortBy) {		
		Pageable pageable = PageRequest.of(page.intValue(), pageSize, Sort.by(sortBy));
		Page<TodoItem> todoPage = todoItemRepository.findAll(pageable);

		return TabulatorDataAdapter.convert(todoPage);
	}


	
	@Override
	public void saveTodoItem(TodoItem todoItem) {
		todoItemRepository.save(todoItem);
	}

	@Override
	public void deleteTodoItemById(Long id) {
		todoItemRepository.deleteById(id);
	}

}
