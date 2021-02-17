package com.test.pranto.client.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.test.pranto.client.view.ToDoGridView;
import com.test.pranto.todoapp.Constants;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class HomeScreen extends VerticalLayout {

	public HomeScreen(UI ui) {
		setSizeFull();

		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%"); //$NON-NLS-1$
		header.setHeight("60px"); //$NON-NLS-1$
		header.setMargin(false);
		header.addStyleName("top-bar-menu"); //$NON-NLS-1$
		addComponent(header);

		HorizontalLayout middlePane = new HorizontalLayout();
		middlePane.setSizeFull();
		CssLayout viewContainer = new CssLayout();
		viewContainer.setSizeFull();

		final Navigator navigator = new Navigator(ui, viewContainer);
		Menu menu = createNavigationMenu(navigator);
		createTopMenuBar(header, menu);

		middlePane.addComponent(menu);
		middlePane.addComponent(viewContainer);
		middlePane.setExpandRatio(viewContainer, 1);
		middlePane.setSizeFull();

		addComponent(middlePane);
		setExpandRatio(middlePane, 1);
		navigator.setErrorView(ErrorView.class);

		// Footer
		HorizontalLayout footer = new HorizontalLayout();
		footer.setWidth("100%"); //$NON-NLS-1$
		footer.setHeight("40px"); //$NON-NLS-1$
		footer.setMargin(false);
		addComponent(footer);
		createFooter(footer);

		menu.setActiveTreeView(ToDoGridView.VIEW_NAME);
	}

	private Menu createNavigationMenu(Navigator navigator) {
		Menu menu = new Menu(navigator);
		menu.addChildTreeItem(ToDoGridView.VIEW_NAME, ToDoGridView.class, null, FontAwesome.CIRCLE, false);
		return menu;
	}

	private void createFooter(HorizontalLayout footer) {
		Label lblCopyright = new Label();
		footer.addComponent(lblCopyright);
		Date now = new Date();
		SimpleDateFormat yearFormatter = new SimpleDateFormat("YYYY"); //$NON-NLS-1$
		String currentYear = yearFormatter.format(now);
		lblCopyright.setValue("Copyright ©" + currentYear + ", Sahabur . All rights reserved."); //$NON-NLS-1$ //$NON-NLS-2$
		lblCopyright.setWidthUndefined();
		lblCopyright.addStyleName("footer-copyright"); //$NON-NLS-1$
		footer.setComponentAlignment(lblCopyright, Alignment.MIDDLE_LEFT);
		footer.setStyleName("footer-layout"); //$NON-NLS-1$
		footer.setExpandRatio(lblCopyright, 1f);
		Label version = new Label();
		footer.addComponent(version);
		version.setWidthUndefined();
		version.addStyleName("footer-version"); //$NON-NLS-1$
		version.setValue("Version: " + Constants.appVersion); //$NON-NLS-1$
		footer.setComponentAlignment(version, Alignment.MIDDLE_RIGHT);
	}

	private void createTopMenuBar(HorizontalLayout layout, Menu menu) {

		Button btnHideMenu = new Button(FontAwesome.BARS);
		btnHideMenu.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		layout.addComponent(btnHideMenu);

		Label lblStoreName = new Label();
		lblStoreName.addStyleName(ValoTheme.LABEL_H3);
		lblStoreName.addStyleName(ValoTheme.LABEL_BOLD);
		lblStoreName.setValue("My todo app");
		lblStoreName.addStyleName("header-store-name"); //$NON-NLS-1$
		lblStoreName.setWidthUndefined();

		layout.addComponent(lblStoreName);
		layout.setComponentAlignment(lblStoreName, Alignment.MIDDLE_LEFT);
		layout.setExpandRatio(lblStoreName, 1f);

		Component buildUserMenu = menu.buildUserMenu();
		buildUserMenu.addStyleName("setting-menu-bar"); //$NON-NLS-1$

		Label spacing = new Label(""); //$NON-NLS-1$
		spacing.setWidth("10px"); //$NON-NLS-1$
		layout.addComponent(spacing);
		layout.addComponent(buildUserMenu);
		layout.setComponentAlignment(buildUserMenu, Alignment.MIDDLE_RIGHT);
		layout.setComponentAlignment(btnHideMenu, Alignment.MIDDLE_CENTER);

		btnHideMenu.addClickListener(event -> menu.setVisible(!menu.isVisible()));

	}

}
