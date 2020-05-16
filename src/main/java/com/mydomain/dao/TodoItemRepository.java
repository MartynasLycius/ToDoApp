package com.mydomain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mydomain.models.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
