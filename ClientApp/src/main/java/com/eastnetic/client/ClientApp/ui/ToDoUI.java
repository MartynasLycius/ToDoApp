package com.eastnetic.client.ClientApp.ui;

import com.eastnetic.client.ClientApp.repo.ToDoRepository;
import com.eastnetic.client.ClientApp.repo.UserRepository;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@SpringViewDisplay
public class ToDoUI extends UI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private final UserRepository repo;
	
	@Autowired
	private final ToDoRepository studentRepository;
	
	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		
		VerticalLayout mainLayout = new VerticalLayout();
		
		LoginView loginView = new LoginView(repo);
		
		mainLayout.addComponent(loginView);
		
		mainLayout.setSizeFull();
		
		mainLayout.setComponentAlignment(loginView, Alignment.MIDDLE_CENTER);
		
		setContent(mainLayout);
		
		ToDoEditor studentEditor = new ToDoEditor(studentRepository);
		
		MainView mainView = new MainView(studentRepository, studentEditor);
		
		// build navigator
		navigator = new Navigator(this, mainLayout);
		
		navigator.addView(loginView.VIEW_NAME, loginView);
		navigator.addView(mainView.VIEW_NAME, mainView);
		navigator.navigateTo(loginView.VIEW_NAME);
		
	}
	
	@Autowired
	public ToDoUI(UserRepository repo, ToDoRepository studentRepository) {
		this.repo = repo;
		this.studentRepository = studentRepository;
		 
	}
}
