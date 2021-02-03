package com.todo.controller;

import com.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/// this repo connected to Task table which stores the data of current todo list task
public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findBytaskNameStartsWithIgnoreCase(String taskName);
}
