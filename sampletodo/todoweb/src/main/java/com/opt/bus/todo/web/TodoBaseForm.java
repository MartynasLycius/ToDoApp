/*****************************************************************************************************************
 *
 *	 File			 : TodoBaseForm.java
 *
  *****************************************************************************************************************/

package com.opt.bus.todo.web;

import java.time.LocalDate;

import com.opt.bus.entity.ToDo;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

/*
 * This class is  TodoBaseForm class   which is  
 *  handling  common ui element of todo UI
 */
abstract class TodoBaseForm extends VerticalLayout {

	private static final long serialVersionUID = -3510704833311279341L;
	TextField txtItemName = new TextField("Item Name");
	TextArea txaItemDescription = new TextArea("Item Description");
	DatePicker dateProduction = new DatePicker("Production Date");

	Binder<ToDo> validateForm() {
		Binder<ToDo> binder = new Binder<>(ToDo.class);

		binder.forField(txtItemName).asRequired("Please enter Item name").bind(ToDo::getItemName, ToDo::setItemName);

		binder.forField(dateProduction)
				.withValidator(localDate -> localDate.isBefore(LocalDate.now()), "Please enter a date in past")
				.bind(ToDo::getProductionDate, ToDo::setProductionDate);

		return binder;
	}

	
}
