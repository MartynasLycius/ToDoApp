package com.hafiz.interview.east.netic.todo.repositories;

import com.hafiz.interview.east.netic.todo.core.crud.ICrudRepository;
import com.hafiz.interview.east.netic.todo.entities.TodoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TodoItemRepository extends ICrudRepository<TodoItem, UUID> {
    Page<TodoItem> findAllByUserId(UUID userId, Pageable pageable);
}
