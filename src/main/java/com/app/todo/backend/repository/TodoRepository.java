package com.app.todo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.todo.backend.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
