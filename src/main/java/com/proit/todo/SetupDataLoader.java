package com.proit.todo;

import com.proit.todo.model.Task;
import com.proit.todo.repository.TaskRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final TaskRepository taskRepository;

    public SetupDataLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        taskRepository.save(new Task("Task 1", "Description 1", LocalDate.now()));
        taskRepository.save(new Task("Task 2", "Description 2", LocalDate.now()));
        taskRepository.save(new Task("Task 23", "Description 3", LocalDate.now()));
        taskRepository.save(new Task("Task 4", "Description 4", LocalDate.now()));
    }
}
