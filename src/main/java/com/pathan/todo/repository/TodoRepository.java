package com.pathan.todo.repository;

import com.pathan.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Pathan on 30-Jan-21.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCreatedBy(String user);
}
