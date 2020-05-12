package com.proit.todoapp.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proit.todoapp.constants.BackendUrl;
import com.proit.todoapp.domains.ToDoItem;
import com.proit.todoapp.domains.ToDoItemResponse;
import com.proit.todoapp.pages.AddItemForm;
import com.proit.todoapp.request.RequestHandler;
import com.proit.todoapp.request.RequestHandlerImpl;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout{
	private static final long serialVersionUID = 1L;
	private RequestHandler<ToDoItemResponse> requestHandler=new RequestHandlerImpl<ToDoItemResponse>();
	
	public MainView() {
		List<ToDoItem> items=new ArrayList<>();	
		
		ResponseEntity<ToDoItemResponse> response= requestHandler.createRequest(BackendUrl.TODO, null, HttpMethod.GET);
		
		ObjectMapper mapper = new ObjectMapper();
		ToDoItemResponse toDoItemResponse=new ToDoItemResponse();
		toDoItemResponse= mapper.convertValue(
		    response.getBody(), 
		    new TypeReference<ToDoItemResponse>(){}
		);
		items=toDoItemResponse.getToDoItems();
		
		Button add = new Button("Add");
		add.addClickListener(listener->{
			UI.getCurrent().navigate(AddItemForm.class);
		});
		add.getElement().setAttribute("theme", "primary");
		
		
		Grid<ToDoItem> grid = new Grid<>(ToDoItem.class);
		grid.setColumns("name", "description","createdAt");
		grid.addColumn(new ComponentRenderer<>(item -> {
			Icon edit = new Icon(VaadinIcon.EDIT);
			edit.getStyle().set("cursor", "pointer");
			edit.getElement().setAttribute("theme", "primary");
			edit.setColor("#1676F3");
			edit.addClickListener(
			        event -> {UI.getCurrent().navigate("edit/" + item.getId());});			
            return edit;
        })).setHeader("Action");
		
		grid.setColumnReorderingAllowed(true);
		grid.setItemDetailsRenderer(
				new ComponentRenderer<>(item -> new VerticalLayout(new Span("Created Date: " + item.getCreatedAt()),
						new Span("Update At: " + item.getUpdatedAt()))));

		grid.setItems(items);
		add(add,grid);
	}	
}
