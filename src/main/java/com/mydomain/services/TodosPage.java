package com.mydomain.services;

import java.util.List;

import com.mydomain.models.TodoItem;

public class TodosPage {

	private int totalPage;
	private List<TodoItem> data;

	public TodosPage() {
	}
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<TodoItem> getData() {
		return data;
	}

	public void setData(List<TodoItem> data) {
		this.data = data;
	}

}