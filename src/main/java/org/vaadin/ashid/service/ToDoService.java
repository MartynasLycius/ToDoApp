package org.vaadin.ashid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.ashid.model.ToDo;
import org.vaadin.ashid.repository.ToDoRepository;

import com.vaadin.flow.data.provider.ListDataProvider;

/*
* @author  Md Ahasanul Ashid
* @version 1.0
* @email:  ashid8bd@gmail.com 
*/
@Service
public class ToDoService implements ToDoServiceInf {

	@Autowired
	ToDoRepository toDoRepository;

	public synchronized void save(ToDo toDo) {
		if (toDo == null) {
			return;
		}
		toDoRepository.save(toDo);
	}

	public synchronized void delete(ToDo toDo) {
		if (toDo == null) {
			return;
		}
		toDoRepository.delete(toDo);
	}

	public synchronized ListDataProvider<ToDo> findAll() {
		List<ToDo> toDoList = toDoRepository.findAll();
		return new ListDataProvider<ToDo>(toDoList);
	}

}
