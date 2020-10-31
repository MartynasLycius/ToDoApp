package com.proit.todo.data.entity;

import com.proit.todo.data.AbstractEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Task extends AbstractEntity {

private String name;
private String description;
private LocalDate taskDate;


public String getName() {
  return name;
}
public void setName(String name) {
  this.name = name;
}
public String getDescription() {
  return description;
}
public void setDescription(String description) {
  this.description = description;
}
public LocalDate getTaskDate() {
  return taskDate;
}
public void setTaskDate(LocalDate taskDate) {
  this.taskDate = taskDate;
}


}
