package com.zahidul.backend.service;

import java.util.List;

import com.zahidul.backend.model.ToDoItem;

public interface ToDoItemService {

	public List<ToDoItem> getAllItem();

	public ToDoItem getItemById(Long itemId);

	public void saveItem(ToDoItem todoItem);

	public void deleteItem(ToDoItem todoItem);
}
