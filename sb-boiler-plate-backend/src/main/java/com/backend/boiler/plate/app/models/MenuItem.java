package com.backend.boiler.plate.app.models;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	private String path;
	private String title;
	private Boolean show;
	private List<MenuItem> subMenu = new ArrayList<MenuItem>();
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getShow() {
		return show;
	}
	public void setShow(Boolean show) {
		this.show = show;
	}
	public List<MenuItem> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<MenuItem> subMenu) {
		this.subMenu = subMenu;
	}
	
}
