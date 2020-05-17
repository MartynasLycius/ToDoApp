package com.todo.persistence.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.todo.persistence.model.audit.DateAudit;

@Entity
@Table(name = "todo")
public class Todo extends DateAudit{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	@NotBlank(message="{NotBlank.todo.title}")
    private String title;
    
    private String description;
    
    private LocalDate dueDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
}
