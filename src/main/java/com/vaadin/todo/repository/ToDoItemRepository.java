package com.vaadin.todo.repository;


import com.vaadin.todo.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemRepository extends JpaRepository<TodoItem, Long> {


}
