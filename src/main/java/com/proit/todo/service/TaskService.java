package com.proit.todo.service;

import com.proit.todo.exception.TaskNotFoundException;
import com.proit.todo.model.Task;
import com.proit.todo.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Page<Task> findAllByName(String name, Pageable pageable) {
        return taskRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task find(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task update(Long id, Task task) {
        Task oldTask = find(id);

        // Setting updatable fields
        oldTask.setName(task.getName());
        oldTask.setDescription(task.getDescription());
        oldTask.setDate(task.getDate());

        return taskRepository.save(oldTask);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
