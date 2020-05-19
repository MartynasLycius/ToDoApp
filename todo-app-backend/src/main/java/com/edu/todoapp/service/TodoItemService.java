package com.edu.todoapp.service;

import com.edu.todoapp.entity.TodoItem;
import com.edu.todoapp.repository.TodoItemRepository;
import com.edu.todoapp.util.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService implements BaseService<TodoItem,Integer> {

    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository){
        this.todoItemRepository = todoItemRepository;
    }


    @Override
    public TodoItem create(TodoItem todoItem) {
        todoItem.setStatus('N');
        todoItem.setCreatedAt(LocalDateTime.now());
        return todoItemRepository.save(todoItem);
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        todoItemRepository.findById(todoItem.getId()).ifPresent(todo-> {
            todo.setItemDate(todoItem.getItemDate());
            todo.setItemName(todoItem.getItemName());
            todo.setDescription(todoItem.getDescription());
            todoItemRepository.save(todo);
        });
        return todoItem;
    }

    @Override
    public Optional<TodoItem> findById(Integer id) {
        return todoItemRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<TodoItem> findAll() {
        return todoItemRepository.findAll();
    }

    @Transactional
    public boolean updateTodoStatus(Integer id, Character status) {
        todoItemRepository.findById(id).ifPresent(todo-> {
            todo.setStatus(status);
            todoItemRepository.save(todo);
        });
        return true;
    }
}
