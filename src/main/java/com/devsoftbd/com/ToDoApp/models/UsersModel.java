package com.devsoftbd.com.ToDoApp.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class UsersModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	@NotEmpty
	@Column(columnDefinition = "varchar(32)", nullable = false)
	@Length(max = 32, min = 3)
	private String username;
	@NotEmpty
	@Length(min = 4, message = "Password should have minimum 4 characters")
	@Column(nullable = false)
	private String password;
	@Column(name = "first_name", columnDefinition = "varchar(64)")
	@Length(max = 64, message = "First name lenght maximum 32 allowed")
	private String firstName;
	@Column(name = "last_name", columnDefinition = "varchar(64)")
	@Length(max = 64, message = "First name lenght maximum 64 allowed")
	private String lastName;
	private boolean enabled;
	@CreationTimestamp
	@Column(name = "creation_date_time")
	private Date creationDateTime;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<TodoItemsModel> todoList;

	public UsersModel() {
	}

	public UsersModel(String username, String password, String firstName, String lastName, boolean enabled) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
	}

	public UsersModel(UsersModel user) {
		this.userId = user.userId;
		this.username = user.username;
		this.password = user.password;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.enabled = user.enabled;
		this.creationDateTime = user.creationDateTime;
		this.todoList = user.todoList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public List<TodoItemsModel> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<TodoItemsModel> todoList) {
		this.todoList = todoList;
	}

	@Override
	public String toString() {
		return "UsersModel [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", enabled=" + enabled + ", creationDateTime="
				+ creationDateTime + "]";
	}
}
