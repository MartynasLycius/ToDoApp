package com.mydomain.services;

import org.springframework.data.domain.Page;

import com.mydomain.models.TodoItem;

public class TabulatorDataAdapter {
	
	public static PagedTodoItems convert(Page<TodoItem> page) {
		PagedTodoItems pagedTodoItems = new PagedTodoItems();
		pagedTodoItems.setData(page.getContent());
		pagedTodoItems.setTotalPage(page.getTotalPages());
		return pagedTodoItems;
	}

}
