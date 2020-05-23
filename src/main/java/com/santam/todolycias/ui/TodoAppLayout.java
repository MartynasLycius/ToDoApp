package com.santam.todolycias.ui;

import com.santam.todolycias.ui.auth.LoginPage;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class TodoAppLayout extends AppLayout {

    public TodoAppLayout() {

        H3 title = new H3("ToDo (ProIT)");
        Image img = new Image("icons/icon.png", "ToDo (ProIT)");
        img.setHeight("44px");
        img.getStyle().set("margin-left","15%");

        Tabs tabs = new Tabs();
        FlexLayout centeredLayout = new FlexLayout();
        centeredLayout.getStyle().set("margin-left","20px");
//        centeredLayout.setSizeFull();
        centeredLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        centeredLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        centeredLayout.add(tabs);

        Tab todoListTab = new Tab(new RouterLink("Home", MainView.class));
        Tab logout = new Tab(new RouterLink("Log Out", LoginPage.class));
        tabs.add(todoListTab, logout);

        addToNavbar(img, title, centeredLayout);
    }
}
