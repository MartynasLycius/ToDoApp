package com.example.demo.service;

import com.example.demo.model.TodoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ItemService {

    TodoItem save(TodoItem todoRequest);

    Page<TodoItem> findAll(PageRequest pageRequest);

    TodoItem update(TodoItem todoRequest, Long id);

    TodoItem findById(Long id);

    TodoItem deleteById(Long id);
}
