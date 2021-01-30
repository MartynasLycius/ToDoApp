package com.shakhawat.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shakhawat.todo.model.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, String>{

	List<ToDo> findAllByOrderByIsDoneAscDateDesc();
}
