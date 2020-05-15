package com.martynaslycius.vaadin.data.interfaces;

import java.util.Date;

import com.martynaslycius.vaadin.data.models.ToDo;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.timepicker.TimePicker;

public interface TodoService {

	ToDo saveTodo(ToDo todo, Date todoDate, String itemName, String description);

	Date getTodoDate(DatePicker datePicker, TimePicker timePicker);

}
