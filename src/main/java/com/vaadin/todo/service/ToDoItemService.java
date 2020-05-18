package com.vaadin.todo.service;


import com.vaadin.todo.entity.ToDoItem;
import com.vaadin.todo.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ToDoItemService {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ToDoItemRepository toDoItemRepository;

    private static final Logger LOGGER = Logger.getLogger(ToDoItemService.class.getName());

    public List<ToDoItem> findAll() {
        return toDoItemRepository.findAll();
    }

}
