package com.opt.bus.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ToDoDto {

	@Id
	private Long id;

	private String name;

	private String description;

	private LocalDate date;

	@Transient
	private String edit;

	@Transient
	private String delete;
	
	public ToDoDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}
	
	
	

}
