package com.proit.todoapp.pages;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.proit.todoapp.constants.BackendUrl;
import com.proit.todoapp.dashboard.MainView;
import com.proit.todoapp.domains.ToDoItem;
import com.proit.todoapp.request.RequestHandler;
import com.proit.todoapp.request.RequestHandlerImpl;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("add")
public class AddItemForm extends VerticalLayout {
	private static final long serialVersionUID = 3L;
	private TextField name = new TextField("Name");	
	private TextArea description = new TextArea("Description");
	private RequestHandler<ToDoItem> reqeustHandler=new RequestHandlerImpl<ToDoItem>();	
	private ToDoItem item=new ToDoItem();
	
	public AddItemForm() {
		Binder<ToDoItem> binder = new BeanValidationBinder<>(ToDoItem.class);
		binder.bindInstanceFields(this);
		binder.setBean(item);		

		Button save = new Button("Save");
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClickListener(event -> {
			ResponseEntity<ToDoItem> response= reqeustHandler.createRequest(BackendUrl.TODO, item, HttpMethod.POST);
			if (response.getStatusCode().equals(HttpStatus.CREATED)) {		
				new Notification("Successfully Save!!!", 3000).open();
				UI.getCurrent().navigate(MainView.class);
			}else {
				new Notification("Please try again!!!", 3000).open();
			}			
		});	
		
		HorizontalLayout buttonsLayout = new HorizontalLayout(save);
		
		VerticalLayout divLayout = new VerticalLayout(name,description, save);
        divLayout.setAlignSelf(Alignment.END, buttonsLayout);
        Div loginDiv = new Div(divLayout);
        setAlignItems(Alignment.CENTER);
        name.focus();
        add(loginDiv);
	}
}
