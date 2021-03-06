/*****************************************************************************************************************
 *
 *	 File			 : MainView.java
 *
  *****************************************************************************************************************/



package com.opt.bus.todo.web;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/*
 * This class is used for starting page.  
 *  
 */
@Route
public class MainView extends VerticalLayout {
	
	private static final long serialVersionUID = 8550502852995623428L;

	public MainView() {
	        
	    }
	
	
	  @PostConstruct
	    void init() {
	   
	       
	        Button gotoToDoitemBtn = new Button("ToDO list");
	        gotoToDoitemBtn.addClickListener(e -> {
	          
	         
	            
	            UI.getCurrent().navigate(ToDoList.class);
	        });
	       
	        
	        HorizontalLayout toolbar = new HorizontalLayout(gotoToDoitemBtn);
	               
	    
	        add(toolbar);
	       
	        setHeight("100vh");
	        
	    
	        
	    }

}
