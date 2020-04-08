package com.devsoftbd.com.ToDoApp.exceptions;

public class UsernameNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException(String username) {
		super(username + " was not found");
	}
}
