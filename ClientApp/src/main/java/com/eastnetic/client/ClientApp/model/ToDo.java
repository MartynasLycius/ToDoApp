package com.eastnetic.client.ClientApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ToDo {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "task")
	private String task;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String eMail;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	public ToDo(){}
	
	public ToDo(String firstName, String lastName, String task, String phone, String eMail, Date creationDate){
		this.firstName = firstName;
		this.lastName = lastName;
		this.task = task;
		this.creationDate = creationDate;
		this.eMail = eMail;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Student[id=%d, firstName='%s',lastName='%s',eMail='%s',creationDate='%s']", id ,firstName, lastName, phone, eMail,creationDate);
	}

}
