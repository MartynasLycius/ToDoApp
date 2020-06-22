package com.backend.boiler.plate.app.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "todos")
@Proxy(lazy = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo implements Serializable {
	
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO,generator = "todo_generator")
    @SequenceGenerator(
            name = "todo_generator",
            sequenceName = "todo_sequence",
            initialValue = 1000,
			allocationSize = 1
    )
	@Column( columnDefinition = "NUMERIC(19,0)")
	private Long id;
	
	@NotEmpty
	@Column(name="title",length=128, nullable=false)
	private String title;
	
	@Column(name="description",length=10000)
	private String description;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "user_id", nullable = false, insertable=true, updatable=true)
    private User user;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
