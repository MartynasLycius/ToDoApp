package com.proit.todo.data.repository;

import com.proit.todo.data.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    Optional<Todo> getTodoByTodoId(String todoId);
}
