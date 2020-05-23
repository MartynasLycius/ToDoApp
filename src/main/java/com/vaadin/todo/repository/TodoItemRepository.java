package com.vaadin.todo.repository;


import com.vaadin.todo.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {


}
