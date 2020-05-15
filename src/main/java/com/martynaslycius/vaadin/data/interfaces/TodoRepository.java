package com.martynaslycius.vaadin.data.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.martynaslycius.vaadin.data.models.ToDo;

@Repository
public interface TodoRepository extends JpaRepository<ToDo, Long> {

}
