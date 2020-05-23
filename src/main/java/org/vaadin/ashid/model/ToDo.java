package org.vaadin.ashid.model;

import java.time.LocalDate;

import javax.persistence.*;

/*
* @author  Md Ahasanul Ashid
* @version 1.0
* @email:  ashid8bd@gmail.com 
*/

@Entity
@Table(name = "ToDo")
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "itemName")
	private String itemName;

	@Column(name = "description")
	private String description;

	@Column(name = "date")
	private LocalDate date;

	public ToDo() {

	}

	public ToDo(String itemName, String description, LocalDate date) {
		this.itemName = itemName;
		this.description = description;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", itemName=" + itemName + ", description=" + description + ", date=" + date + "]";
	}
	
	

}