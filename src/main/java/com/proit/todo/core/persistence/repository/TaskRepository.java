package com.proit.todo.core.persistence.repository;

import com.proit.todo.core.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaRepository<Task, Integer>,
        JpaSpecificationExecutor<Task> {
    Task getDistinctById(int id);
}
