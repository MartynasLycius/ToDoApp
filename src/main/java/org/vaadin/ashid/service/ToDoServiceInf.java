package org.vaadin.ashid.service;

import org.vaadin.ashid.model.ToDo;

import com.vaadin.flow.data.provider.ListDataProvider;

/*
* @author  Md Ahasanul Ashid
* @version 1.0
* @email:  ashid8bd@gmail.com 
*/
public interface ToDoServiceInf {
	public abstract void save(ToDo toDo);

	public abstract void delete(ToDo toDo);

	public abstract ListDataProvider<ToDo> findAll();

}
