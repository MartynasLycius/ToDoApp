package org.vaadin.ashid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.ashid.model.ToDo;
/*
* @author  Md Ahasanul Ashid
* @version 1.0
* @email:  ashid8bd@gmail.com 
*/
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
