package com.martynaslycius.vaadin.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martynaslycius.vaadin.data.interfaces.TodoRepository;
import com.martynaslycius.vaadin.data.interfaces.TodoService;
import com.martynaslycius.vaadin.data.models.ToDo;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.timepicker.TimePicker;

@Service
public class TodoServiceImpl implements TodoService {

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public ToDo saveTodo(ToDo todo, Date todoDate, String itemName, String description) {
		logger.debug("todo date is "+ todoDate);
		todo.setTodoDate(todoDate);
		todo.setItemName(itemName);
		todo.setDescription(description);
		return todoRepository.save(todo);
	}

	@Override
	public Date getTodoDate(DatePicker datePicker, TimePicker timePicker) {
		try {
			if (timePicker.isEmpty()) {
				timePicker.setValue(LocalTime.MIDNIGHT);
			}
			logger.debug("saving datepicker value " + datePicker.getValue() +" and time " + timePicker.getValue());
			return dateFormat.parse(datePicker.getValue() + " " + timePicker.getValue());
		} catch (ParseException e) {
			logger.error("Parse exception ", e);
		}
		return null;
	}
}
