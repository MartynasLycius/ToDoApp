/*****************************************************************************************************************
 *
 *	 File			 : ToDoNew.java
 *
  *****************************************************************************************************************/


package com.opt.bus.todo.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.opt.bus.entity.ToDo;
import com.opt.bus.service.IToDoService;
import com.opt.common.enumeration.Status;
import com.opt.exception.BusinessException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;


/*
 * This class is  ToDoNew class   which is  used for save TODO.
 * 
 */
@Route(value = "toDoNew")
public class ToDoNew extends TodoBaseForm {

	private static final long serialVersionUID = -6012864169196002791L;

	@Inject
	private IToDoService iToDoService;
	
	private static final Logger lOGGER = Logger.getLogger(ToDoNew.class.getName());

	@PostConstruct
	public void init() {
		Button saveBtn = new Button("save");

		setWidth("720px");
		setClassName("auto-margin");

		saveBtn.addClickListener(e -> {
			if (validateForm().validate().isOk()) {

				ToDo newToDo = new ToDo();
				newToDo.setItemName(txtItemName.getValue());
				newToDo.setItemDescription(txaItemDescription.getValue());
				newToDo.setProductionDate(dateProduction.getValue());
				newToDo.setStatusId(Status.ACTIVE.getValue());

				try {
					iToDoService.save(newToDo);
					lOGGER.log(Level.INFO, "new toDo created."+newToDo.getId());
				} catch (BusinessException be) {
					lOGGER.log(Level.SEVERE, "Error occurd in save.", be.getMessage());
				}

				UI.getCurrent().navigate("toDoItemList");

			}

		});

		
		add(txtItemName, txaItemDescription, dateProduction, saveBtn);
		
	}

}
