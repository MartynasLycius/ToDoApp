package com.zahidul.backend.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahidul.backend.model.ToDoItem;
import com.zahidul.backend.repository.ToDoItemRepository;
import com.zahidul.backend.service.ToDoItemService;

@Service
public class ToDoItemServiceImpl implements ToDoItemService {

	@Autowired
	private ToDoItemRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(ToDoItemServiceImpl.class);

	@Override
	public List<ToDoItem> getAllItem() {
		return repository.findAll();
	}

	@Override
	public ToDoItem getItemById(Long itemId) {
		return repository.findById(itemId).get();
	}

	@Override
	public void saveItem(ToDoItem todoItem) {
		if (todoItem != null) {
			repository.save(todoItem);
			logger.info("Item saved successfully, Item Details=" + todoItem);
		}
	}

	@Override
	public void deleteItem(ToDoItem todoItem) {
		if (todoItem != null) {
			repository.delete(todoItem);
			logger.info("Item deleted successfully, Item Details=" + todoItem);
		}
	}
}
