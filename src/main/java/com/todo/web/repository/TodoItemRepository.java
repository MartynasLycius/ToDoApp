package com.todo.web.repository;

import com.todo.web.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TodoItemRepository class for JPA operation
 *
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 2020-20-05
 */
@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    /**
     * Get all totoItem list by targetDate desc order.
     *
     * @return List TodoItem
     */
    List<TodoItem> findByOrderByTargetDateAsc();

}
