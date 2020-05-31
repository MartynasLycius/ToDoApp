package com.todo.task.service;

import com.todo.task.entity.Task;
import com.todo.user.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    void save(Task task);
    void delete(Task task);
    List<Task> findAll();
    List<Task> findByDate(LocalDate date);
    List<Task> findAllByUser(User user);
    List<Task> findAllByUserAndDate(User user, LocalDate date);
}
