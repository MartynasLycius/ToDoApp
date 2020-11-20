package com.vaadin.zahid.dao;

import com.vaadin.zahid.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task,Long> {

}
