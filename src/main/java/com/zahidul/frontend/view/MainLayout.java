package com.zahidul.frontend.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Nav;

public class MainLayout extends AppLayout {

	public MainLayout() {
		addToNavbar(createHeaderContent());
	}

	private Component createHeaderContent() {
		Header header = new Header();
		header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "flex-col", "w-full");

		Div layout = new Div();
		layout.addClassNames("flex", "h-xl", "items-center", "px-l");

		H1 appName = new H1("ToDo App");
		appName.addClassNames("my-0", "me-auto", "text-l");
		layout.add(appName);

		Nav nav = new Nav();
		nav.addClassNames("flex", "gap-s", "overflow-auto", "px-m");

		header.add(layout, nav);
		return header;
	}

}
