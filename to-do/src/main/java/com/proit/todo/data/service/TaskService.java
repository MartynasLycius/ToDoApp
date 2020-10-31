package com.proit.todo.data.service;

import com.proit.todo.data.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class TaskService extends CrudService<Task, Integer> {

    private TaskRepository repository;

    public TaskService(@Autowired TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    protected TaskRepository getRepository() {
        return repository;
    }

}
