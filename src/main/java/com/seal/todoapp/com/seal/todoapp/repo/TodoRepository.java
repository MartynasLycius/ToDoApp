package com.seal.todoapp.com.seal.todoapp.repo;

import com.seal.todoapp.com.seal.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
