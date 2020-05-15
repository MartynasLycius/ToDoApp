package com.martynaslycius.vaadin.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends VerticalLayout implements RouterLayout, BeforeEnterObserver {

	private static final long serialVersionUID = 1L;

	public MainLayout() {
		RouterLink addFormLink = new RouterLink("Add Todo", AddTodoForm.class);
		RouterLink todosLink = new RouterLink("All Todos", MainView.class);

		add(new H1("Vaadin Todo"), new HorizontalLayout(todosLink, addFormLink));

	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
	}

}
