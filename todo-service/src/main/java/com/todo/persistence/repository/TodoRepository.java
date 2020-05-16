package com.todo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.persistence.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
