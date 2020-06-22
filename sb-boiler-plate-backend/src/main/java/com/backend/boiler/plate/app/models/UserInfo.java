package com.backend.boiler.plate.app.models;

import java.util.List;

public class UserInfo {
	private User user;
	private List<MenuItem> accessMenu;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<MenuItem> getAccessMenu() {
		return accessMenu;
	}
	public void setAccessMenu(List<MenuItem> accessMenu) {
		this.accessMenu = accessMenu;
	}
	
}
