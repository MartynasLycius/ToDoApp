package com.eastnetic.client.ClientApp.ui;

import com.eastnetic.client.ClientApp.model.User;
import com.eastnetic.client.ClientApp.repo.UserRepository;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends CustomComponent implements View{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_NAME = "login";

	private final UserRepository userRepository;
	
	public User user;
	
	//private Navigator navigator;
	
	TextField userName = new TextField("username");
	TextField password = new TextField("password");
	Button login = new Button("Login");
	
	Binder<User> binder = new Binder<User>();
	
	
	@Autowired
	public LoginView(UserRepository userRepository) {
		this.userRepository = userRepository;
		//this.navigator =  getUI().getNavigator();
		
		// Create a panel with a caption.
		Panel loginPanel = new Panel("Login");
		loginPanel.setSizeUndefined();
		loginPanel.setWidth(null);
		
		// Create a layout inside the panel
		final FormLayout loginLayout = new FormLayout();
		// Add some components inside the layout
		loginLayout.addComponent(userName);
		loginLayout.addComponent(password);
		loginLayout.addComponent(login);
		
		setSizeUndefined();
		// Have some margin around it.
		loginLayout.setMargin(true);
		
		// set required indicator for text fields
		userName.setRequiredIndicatorVisible(true);
		password.setRequiredIndicatorVisible(true);
		
		
		// Set the layout as the root layout of the panel
		 
		loginPanel.setContent(loginLayout);
		
		setCompositionRoot(loginPanel);
		

		// login button onclick event implementation
		login.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				String userNameVal = userName.getValue();
				String passVal = password.getValue();
				User loggedUser = userRepository.findByUserNameAndPassword(userNameVal, passVal);
//				System.out.println("-----" + loggedUser.getUserName() + loggedUser.getPassword()+ "-----");
				getUI().getNavigator().navigateTo(MainView.VIEW_NAME);
	
			}
		});
		
		
		
	}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
