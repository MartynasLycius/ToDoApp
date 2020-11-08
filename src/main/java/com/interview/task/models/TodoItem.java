package com.interview.task.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.task.ToDoTrackerAppApplication;

@Entity
public class TodoItem {

	static final Logger logger = LoggerFactory.getLogger(ToDoTrackerAppApplication.class);

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long taskId;
	
	@NotNull
	private String taskName;
	
	private String description;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime dateCreated;
	
	private LocalDateTime dateToBePerformed;
	
	@NotNull
	private boolean isComplete;
	
	public TodoItem() {}
		
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateToBePerformed() {
		return dateToBePerformed;
	}

	public void setDateToBePerformed(LocalDateTime dateToBePerformed) {
		this.dateToBePerformed = dateToBePerformed;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
