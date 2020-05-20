package com.app.todo.frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MainLayout extends AppLayout{
	public MainLayout() {
		createHeader();
	}
	
	private void createHeader() {
        H1 logo = new H1("Todo App");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo); 

        header.setWidth("100%");
        header.addClassName("header");


        addToNavbar(header); 

    }
}
