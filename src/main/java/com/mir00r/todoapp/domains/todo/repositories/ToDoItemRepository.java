package com.mir00r.todoapp.domains.todo.repositories;

import com.mir00r.todoapp.domains.todo.models.entities.ToDoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    @Query("SELECT tdi FROM ToDoItem tdi WHERE (:q IS NULL OR tdi.name LIKE %:q%) AND tdi.deleted=false")
    Page<ToDoItem> search(@Param("q") String query, Pageable pageable);

    @Query("SELECT tdi FROM ToDoItem tdi WHERE tdi.id=:id AND tdi.deleted=false")
    Optional<ToDoItem> find(@Param("id") Long id);
}
