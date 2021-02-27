package com.opt.bus.todo.web;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.opt.bus.entity.ToDo;
import com.opt.bus.service.IToDoService;
import com.opt.exception.BusinessException;
import com.opt.exception.NoDataException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;



@Route
public class ToDoEdit extends Div implements HasUrlParameter<String> {

	private static final long serialVersionUID = -5435018840644491116L;

	@Inject
	private IToDoService iToDoService;

	private TextField name = new TextField("Name");
	private TextField description = new TextField("Description");
	private DatePicker date=new DatePicker("date");

	private Binder<ToDo> binder = new BeanValidationBinder<>(ToDo.class);

	private ToDo toDo;
	Button editButton = new Button("save");

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		//setText(String.format("Hello,----------CustomerEdit----------------------- %s!", parameter));

		try {
			toDo = iToDoService.getToDo(Long.valueOf(parameter));
		} catch (NumberFormatException | NoDataException e) {
			
		}
		name.setValue(toDo.getName());
		description.setValue(toDo.getDescription());
		date.setValue(toDo.getDate());
		
		add(name, description,date,editButton);

	}

	@PostConstruct
	public void inits() {
	
		editButton.addClickListener(e -> {

			
			toDo.setName(binder.getBean().getName());
			toDo.setDescription(binder.getBean().getDescription());
			toDo.setDate(binder.getBean().getDate());
			
			try {
				iToDoService.save(toDo);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				
			}
			UI.getCurrent().navigate(ToDoList.class);
			binder.setBean(new ToDo());

		});

		//editButton.addClickShortcut(Key.ENTER);
		binder.bindInstanceFields(this);
		//binder.addStatusChangeListener(e -> editButton.setEnabled(binder.isValid()));
		binder.setBean(new ToDo());
	}

}
