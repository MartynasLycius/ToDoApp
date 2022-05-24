package com.todo.app.core.service;

import com.todo.app.core.data.TodoItemRepository;
import com.todo.app.core.entity.TodoItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    TodoItemRepository repository;

    public TodoItemServiceImpl(TodoItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public TodoItem add(TodoItem item) {
        return repository.save(item);
    }

    @Override
    public List<TodoItem> getAll() {
        return repository.findAll();
    }

    @Override
    public TodoItem findById(long id) {
        return repository.findById(id).get();
    }

    public TodoItem update(TodoItem task) {
        return repository.save(task);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
