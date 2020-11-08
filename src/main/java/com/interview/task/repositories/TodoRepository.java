package com.interview.task.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.interview.task.models.TodoItem;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
	Page<TodoItem> findAll(Pageable pageable);
	
	
	@Query("SELECT todo FROM TodoItem todo WHERE todo.isComplete = true")
	List<TodoItem> findAllCompleteTodos();
	
	
	@Transactional
	@Modifying
	@Query("UPDATE TodoItem todo SET todo.isComplete = true where todo.taskId in ?1")
	void updateCompleteState(List<Long> ids);
	
	@Transactional
	@Modifying
	@Query("DELETE from TodoItem todo where todo.taskId in ?1")
	void deleteTasksWithIds(List<Long> ids);
	
	void deleteById(long taskId);
}
