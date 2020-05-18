package com.vaadin.todo.service;


import com.vaadin.todo.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ToDoItemService {

    @Autowired
    private MessageSource messageSource;

    private static final Logger LOGGER = Logger.getLogger(ToDoItemService.class.getName());
    private ToDoItemRepository toDoItemRepository;

}
