package com.edu.todoapp.repository;

import com.edu.todoapp.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem,Integer> {
}
