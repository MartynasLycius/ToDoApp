package com.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/// Task table entity is connected with TaskRepository
@Entity
public class Task {

	@Id
	@GeneratedValue
	private Integer id; // primary key of task table

	/// table columns
	private String taskName;
	private String taskDesc;
	private Timestamp createDate;

	// Default constructor
	protected Task() {
	}

	public Task(String taskName, String taskDesc) {
		this.taskName = taskName;
		this.taskDesc = taskDesc;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}


	public Integer getId() {
		return id;
	}

	public String gettaskName() {
		return taskName;
	}

	public void settaskName(String taskName) {
		this.taskName = taskName;
	}

	public String gettaskDesc() {
		return taskDesc;
	}

	public void settaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	@Override
	public String toString() {
		return String.format("Task[id=%d, taskName='%s', taskDesc='%s']", id,
				taskName, taskDesc);
	}

}
