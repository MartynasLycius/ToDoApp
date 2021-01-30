package com.shakhawat.todo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shakhawat.todo.model.ToDo;

public interface ToDoService {
	
	List<ToDo> getAllToDos();
	
	void saveToDo(ToDo toDo);
	
	ToDo findToDoById(String id);
	
	void updateToDo(ToDo toDo);
	
	Page<ToDo> findPaginated(Pageable pageable);

}
