/*****************************************************************************************************************
 *
 *	 File			 : ToDoEdit.java
 *
  *****************************************************************************************************************/


package com.opt.bus.todo.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.opt.bus.entity.ToDo;
import com.opt.bus.service.IToDoService;
import com.opt.exception.BusinessException;
import com.opt.exception.NoDataException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

/*
 * This class is  ToDoEdit class   which is  used for update TODO.
 * 
 */
@Route(value = "toDoEdit")
public class ToDoEdit extends TodoBaseForm implements HasUrlParameter<Long> {
	private static final long serialVersionUID = -5435018840644491116L;

	@Inject
	private IToDoService iToDoService;

	private ToDo editToDo;
	
	private static final Logger lOGGER = Logger.getLogger(ToDoEdit.class.getName());

	@Override
	public void setParameter(BeforeEvent event, Long parameter) {

		try {
			editToDo = iToDoService.getToDo(Long.valueOf(parameter));
			lOGGER.log(Level.INFO, "toDo retrieve."+editToDo.getId());
		} catch (NumberFormatException | NoDataException e) {
			lOGGER.log(Level.SEVERE, "No toDo found.", e.getMessage());
		}

		txtItemName.setValue(editToDo.getItemName());
		txaItemDescription.setValue(editToDo.getItemDescription());
		dateProduction.setValue(editToDo.getProductionDate());

	}

	@PostConstruct
	public void init() {

		setWidth("720px");
		setClassName("auto-margin");
		Button editBtn = new Button("save");
	
		editBtn.addClickListener(e -> {
			
			editToDo.setId(editToDo.getId());
			editToDo.setItemName(txtItemName.getValue());
			editToDo.setItemDescription(txaItemDescription.getValue());
			editToDo.setProductionDate(dateProduction.getValue());

			try {
				iToDoService.save(editToDo);
				lOGGER.log(Level.INFO, "toDo modified."+editToDo.getId());
			} catch (BusinessException be) {
				
				lOGGER.log(Level.SEVERE, "Error occurd in edit.", be.getMessage());

			}
			UI.getCurrent().navigate("toDoItemList");

		});

		add(txtItemName, txaItemDescription, dateProduction, editBtn);

	}

}
