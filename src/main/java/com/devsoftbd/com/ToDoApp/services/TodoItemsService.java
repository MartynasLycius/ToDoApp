package com.devsoftbd.com.ToDoApp.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.devsoftbd.com.ToDoApp.dao.TodoItemsDAO;
import com.devsoftbd.com.ToDoApp.dto.ReturnObject;
import com.devsoftbd.com.ToDoApp.enums.TodoItemStatus;
import com.devsoftbd.com.ToDoApp.models.TodoItemsChangeLogModel;
import com.devsoftbd.com.ToDoApp.models.TodoItemsModel;
import com.devsoftbd.com.ToDoApp.repositories.TodoItemsChangeLogRepository;
import com.devsoftbd.com.ToDoApp.repositories.TodoItemsRepository;

@Service
public class TodoItemsService {
	private static Logger logger = LoggerFactory.getLogger(TodoItemsService.class.getName());

	@Autowired
	private TodoItemsRepository todoItemRepository;
	@Autowired
	private TodoItemsDAO todoItemDAO;
	@Autowired
	private TodoItemsChangeLogRepository changeLogRepository;

	@Autowired
	private MessageSource msgSource;

	public TodoItemsModel save(TodoItemsModel todoItem) {
		return todoItemRepository.save(todoItem);
	}

	/**
	 * Process all criteria to find filtered todoList to send over network
	 * 
	 * @return
	 */
	public List<TodoItemsModel> findTodoList(TodoItemStatus status, Date startDate, Date endDate) {
		Map<String, Object> criteria = new HashMap<>(3);
		if (status != null)
			criteria.put("status", status);
		if (startDate != null)
			criteria.put("startDate", startDate);
		if (endDate != null)
			criteria.put("endDate", endDate);
		return todoItemDAO.getTodoListFromDynamicCriteria(criteria);
	}

	public TodoItemsModel getById(Integer itemId) {
		Optional<TodoItemsModel> todoItem = todoItemRepository.findByItemId(itemId);
		if (todoItem.isPresent()) {
			return todoItem.get();
		}
		return null;
	}

	public boolean deleteByItemId(Integer itemId) {
		try {
			todoItemRepository.deleteById(itemId);
			return true;
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return false;
	}

	/**
	 * Update existing one if id found or create new one
	 * 
	 * @param todoItem
	 * @return
	 */
	public ReturnObject update(TodoItemsModel todoItem, Locale locale) {
		ReturnObject ro = new ReturnObject();
		try {
			Optional<TodoItemsModel> fetchExistingModel = todoItemRepository.findByItemId(todoItem.getItemId());
			if (fetchExistingModel.isPresent()) {
				if (checkIfFieldChanged(todoItem, fetchExistingModel.get())) {
					TodoItemsModel updateModel = fetchExistingModel.get();
					updateModel.setDescription(todoItem.getDescription());
					updateModel.setItemTitle(todoItem.getItemTitle());
					updateModel.setStatus(todoItem.getStatus());
					todoItemRepository.save(updateModel);
					ro.setSuccessfull(true);
				}
			} else {
				ro.setMessage(msgSource.getMessage("system.message.todoItemNotFound",
						new String[] { "" + todoItem.getItemId() }, locale));
			}
		} catch (Exception e) {
			logger.error("Exception", e);
			ro.setMessage(msgSource.getMessage("system.message.systemError", null, locale));
		}
		return ro;
	}

	/**
	 * Check if required field changed If changed then update data If status change
	 * then keep a log into log table
	 * 
	 * @param changedModel
	 * @param currentModel
	 * @return
	 */
	private boolean checkIfFieldChanged(TodoItemsModel changedModel, TodoItemsModel currentModel) {
		if (!changedModel.getStatus().equals(currentModel.getStatus())) {
			TodoItemsChangeLogModel statusLog = new TodoItemsChangeLogModel(currentModel.getStatus(),
					changedModel.getStatus(), currentModel);
			changeLogRepository.save(statusLog);
			return true;
		}
		if (!changedModel.getItemTitle().equalsIgnoreCase(currentModel.getItemTitle()))
			return true;
		if (!changedModel.getDescription().equalsIgnoreCase(currentModel.getDescription()))
			return true;
		return false;
	}
}
