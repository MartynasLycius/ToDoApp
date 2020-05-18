package com.eastnetic.client.ClientApp.repo;


import com.eastnetic.client.ClientApp.model.ToDo;
import com.sun.xml.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
	
	List<ToDo> findByLastNameStartsWithIgnoreCase(String lastName);
	
	List<ToDo> findByFirstNameStartsWithIgnoreCase(String firstName);
	List<ToDo> findByTaskStartsWithIgnoreCase(String firstName);

	List<ToDo> findById(long id);


}
