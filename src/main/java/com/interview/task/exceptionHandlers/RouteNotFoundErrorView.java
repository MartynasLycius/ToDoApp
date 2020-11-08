package com.interview.task.exceptionHandlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;

import com.interview.task.ui.views.TaskDashBoardView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.RouterLink;

public class RouteNotFoundErrorView extends VerticalLayout implements HasErrorParameter<NotFoundException> {
	
    private Label errorLabel;
    private Icon meh;
    private HorizontalLayout horizontalLayout;
    private Environment env;
    
    public RouteNotFoundErrorView(Environment env) {
		this.env = env;
	}
    
    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
    	meh = new Icon(VaadinIcon.MEH_O);
    	meh.getStyle().set("margin-left", "auto");
    	meh.getStyle().set("margin-right", "auto");
    	meh.setSize("200px");
    	
    	errorLabel = new Label(env.getProperty("routing.error.text"));
    	errorLabel.getStyle().set("font-size", "40px");
    	errorLabel.getStyle().set("margin-left", "auto");
    	errorLabel.getStyle().set("margin-right", "auto");
    	
    	RouterLink dashBoardLink = new RouterLink("Dashboard", TaskDashBoardView.class);
    	dashBoardLink.getStyle().set("font-size", "40px");
    	
    	
    	horizontalLayout = new HorizontalLayout();
    	horizontalLayout.getStyle().set("margin-left", "auto");
    	horizontalLayout.getStyle().set("margin-right", "auto");
    	horizontalLayout.add(errorLabel, dashBoardLink);

    	add(meh, horizontalLayout);
    	
    	return HttpServletResponse.SC_NOT_FOUND;
    }

}