package com.backend.boiler.plate.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
@Proxy(lazy = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails, Serializable {
	
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO,generator = "users_generator")
    @SequenceGenerator(
            name = "users_generator",
            sequenceName = "users_sequence",
            initialValue = 1000,
			allocationSize = 1
    )
	
	@Column( columnDefinition = "NUMERIC(19,0)")
	private Long id;
	
	
	@NotEmpty
	@Column(name="first_name",length=128, nullable=false)
	private String firstName;
	
	@NotEmpty
	@Column(name="last_name", length=128, nullable=false)
	private String lastName;
	
	@NotEmpty
	@Column(name="user_name", length=128, nullable=false)
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty
	@Column(length = 128, unique = true, nullable = false)
	private String email; // required; unique

	@NotEmpty
	@Size(min=8, max=128)
	@Column(length = 128, nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Column
	private boolean enabled;
	
	@Column(name="change_password")
	private boolean changePassword;


	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "user_role", 
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
    private List<Todo> todos = new ArrayList<Todo>();

	public User merge(User user) {
		this.id = user.id;
		this.password = user.password;
		this.roles = user.roles;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.enabled = user.enabled;
		return this;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> rls = new ArrayList<>();
		for (Role role : roles) {
			rls.add(new SimpleGrantedAuthority(role.getName()));
		}
		return rls;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getUserName() {
		return username;
	}
	
	@Override
	public String getUsername() {
		return username;
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

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}
}
