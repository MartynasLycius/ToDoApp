package com.example.demo.service.impl;


import com.example.demo.model.TodoItem;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public TodoItem save(TodoItem todoRequest) {
        return itemRepository.save(todoRequest);
    }

    @Override
    public Page<TodoItem> findAll(PageRequest pageRequest) {
        return itemRepository.findAll( pageRequest);
    }

    @Override
    public TodoItem update(TodoItem todoRequest, Long id) {
        itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Item found"));
        todoRequest.setId(id);
        return itemRepository.save(todoRequest);
    }

    @Override
    public TodoItem findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Item found"));
    }

    @Override
    public TodoItem deleteById(Long id) {
        TodoItem item =  itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Item found"));
        itemRepository.deleteById(id);
        return item;
    }
}
