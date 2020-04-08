package com.devsoftbd.com.ToDoApp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import com.devsoftbd.com.ToDoApp.enums.TodoItemStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "todo_items")
public class TodoItemsModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Integer itemId;
	@NotEmpty
	@Column(name = "item_title", nullable = false)
	private String itemTitle;
	@Column(columnDefinition = "text")
	private String description;
	@CreationTimestamp
	@Column(name = "creation_date_time")
	private Date creationDateTime;
	@Enumerated(EnumType.STRING)
	private TodoItemStatus status;

	@JsonBackReference
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private UsersModel user;

	@JsonManagedReference
	@OneToMany(mappedBy = "statusChangeLog", cascade = CascadeType.REMOVE)
	private List<TodoItemsChangeLogModel> statusChangeLogList;

	public TodoItemsModel() {
	}

	public TodoItemsModel(String itemTitle, String description, TodoItemStatus status, UsersModel user) {
		this.itemTitle = itemTitle;
		this.description = description;
		this.status = status;
		this.user = user;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public TodoItemStatus getStatus() {
		return status;
	}

	public void setStatus(TodoItemStatus status) {
		this.status = status;
	}

	public UsersModel getUser() {
		return user;
	}

	public void setUser(UsersModel user) {
		this.user = user;
	}

	public List<TodoItemsChangeLogModel> getStatusChangeLogList() {
		return statusChangeLogList;
	}

	public void setStatusChangeLogList(List<TodoItemsChangeLogModel> statusChangeLogList) {
		this.statusChangeLogList = statusChangeLogList;
	}

	@Override
	public String toString() {
		return "TodoItemsModel [itemId=" + itemId + ", itemTitle=" + itemTitle + ", description=" + description
				+ ", creationDateTime=" + creationDateTime + ", status=" + status + ", user=" + user
				+ ", statusChangeLogList=" + statusChangeLogList + "]";
	}

}
