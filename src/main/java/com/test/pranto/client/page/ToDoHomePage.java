package com.test.pranto.client.page;

import com.test.pranto.client.ui.HomeScreen;
import com.test.pranto.todoapp.Constants;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("mytheme")
public class ToDoHomePage extends UI {
	private String rootPath = "";//$NON-NLS-1$

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle(Constants.appFullName);

		rootPath = vaadinRequest.getContextPath();

		showMainView();
	}

	protected void showMainView() {
		setContent(new HomeScreen(ToDoHomePage.this));
	}

	public static ToDoHomePage get() {
		return (ToDoHomePage) UI.getCurrent();
	}

	public String getRootPath() {
		return rootPath;
	}
}
