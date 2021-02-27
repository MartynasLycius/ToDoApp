package com.opt.bus.todo.web;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.opt.bus.entity.ToDo;
import com.opt.bus.service.IToDoService;
import com.opt.common.enumeration.Status;
import com.opt.exception.BusinessException;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route
public class ToDoNew extends VerticalLayout {

	private static final long serialVersionUID = -6012864169196002791L;

	@Inject
	private IToDoService iToDoService;

	private Binder<ToDo> binder = new BeanValidationBinder<>(ToDo.class);

	private TextField name = new TextField("Name");
	private TextField description = new TextField("Description");
	private DatePicker date=new DatePicker("date");

	@PostConstruct
	public void inits() {
		Button submit = new Button("save");
		setDefaultHorizontalComponentAlignment(FlexLayout.Alignment.CENTER);

		add(name, description,date ,submit);

		submit.addClickListener(e -> {
			
			
			ToDo newOb = new ToDo();
			newOb.setName(binder.getBean().getName());
			newOb.setDescription(binder.getBean().getDescription());
			newOb.setDate(date.getValue());
			newOb.setStatusId(Status.ACTIVE.getValue());

			try {
				iToDoService.save(newOb);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				
			}

			UI.getCurrent().navigate(ToDoList.class);
			init();

		});

		submit.addClickShortcut(Key.ENTER);
		binder.bindInstanceFields(this);
		binder.addStatusChangeListener(e -> submit.setEnabled(binder.isValid()));
		init();
	}

	private void init() {
		binder.setBean(new ToDo());
	}

}
