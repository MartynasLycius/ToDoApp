package com.vaadin.todo.service;


import com.vaadin.todo.entity.TodoItem;
import com.vaadin.todo.execption.internalServerErrorException;
import com.vaadin.todo.execption.notFoundException;
import com.vaadin.todo.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ToDoItemService {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ToDoItemRepository toDoItemRepository;

    private static final Logger LOGGER = Logger.getLogger(ToDoItemService.class.getName());

    public List<TodoItem> findAll() {
        return toDoItemRepository.findAll();
    }

    public void save(TodoItem toDoItem) throws Exception {
        try {
            if (toDoItem == null) {
                LOGGER.log(Level.SEVERE, messageSource.getMessage("todo.not.found", null, null));
                throw new notFoundException(messageSource.getMessage("todo.not.found", null, null));
            }
            toDoItemRepository.save(toDoItem);
        }catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            throw new internalServerErrorException(messageSource.getMessage("internal.server.error", null, null));
        }
    }

}
