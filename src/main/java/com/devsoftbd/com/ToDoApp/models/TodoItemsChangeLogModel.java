package com.devsoftbd.com.ToDoApp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.devsoftbd.com.ToDoApp.enums.TodoItemStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "todo_items_status_change_log")
public class TodoItemsChangeLogModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "change_log_id")
	private Integer changeLogId;
	@Enumerated(EnumType.STRING)
	private TodoItemStatus prevStatus;
	@Column(name = "changed_status")
	@Enumerated(EnumType.STRING)
	private TodoItemStatus changedStatus;
	@CreationTimestamp
	@Column(name = "creation_date_time")
	private Date creationDateTime;
	@JsonBackReference
	@JoinColumn(name = "item_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private TodoItemsModel statusChangeLog;

	public TodoItemsChangeLogModel() {
	}

	public TodoItemsChangeLogModel(TodoItemStatus prevStatus, TodoItemStatus changedStatus,
			TodoItemsModel statusChangeLog) {
		this.prevStatus = prevStatus;
		this.changedStatus = changedStatus;
		this.statusChangeLog = statusChangeLog;
	}

	public Integer getChangeLogId() {
		return changeLogId;
	}

	public void setChangeLogId(Integer changeLogId) {
		this.changeLogId = changeLogId;
	}

	public TodoItemStatus getPrevStatus() {
		return prevStatus;
	}

	public void setPrevStatus(TodoItemStatus prevStatus) {
		this.prevStatus = prevStatus;
	}

	public TodoItemStatus getChangedStatus() {
		return changedStatus;
	}

	public void setChangedStatus(TodoItemStatus changedStatus) {
		this.changedStatus = changedStatus;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public TodoItemsModel getStatusChangeLog() {
		return statusChangeLog;
	}

	public void setStatusChangeLog(TodoItemsModel statusChangeLog) {
		this.statusChangeLog = statusChangeLog;
	}

	@Override
	public String toString() {
		return "TodoItemsChangeLogModel [changeLogId=" + changeLogId + ", prevStatus=" + prevStatus + ", changedStatus="
				+ changedStatus + ", creationDateTime=" + creationDateTime + "]";
	}

}
