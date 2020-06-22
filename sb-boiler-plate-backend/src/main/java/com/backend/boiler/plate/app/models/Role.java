package com.backend.boiler.plate.app.models;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author bitto kazi
 */

@Entity
public class Role implements Serializable{
    
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO,generator = "role_generator")
    @SequenceGenerator(
            name = "role_generator",
            sequenceName = "role_sequence",
            initialValue = 1000,
			allocationSize = 1
    )
	@Column(
		    columnDefinition = "NUMERIC(19,0)"
		)
    private Long id;

    @Column(unique = true, nullable = false, length=128)
    private String name;
	
	@Column(length = 64)
    private String description;

    /*
     * Role model Constructor
     */
	public Role merge(Role role) {
		this.id = role.id;
		this.name = role.name;
        this.description = role.description;
        return this;
    }

	/*
	 * Getters and Setters Implemented
	 */
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
}

