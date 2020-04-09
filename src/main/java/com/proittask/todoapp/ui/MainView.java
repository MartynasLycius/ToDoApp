package com.proittask.todoapp.ui;

import com.proittask.todoapp.ui.views.list.DashboardView;
import com.proittask.todoapp.ui.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

@PWA(name = "To Do Application",
        shortName = "To Do App",
        description = "This is a Task to be completed for ProIT",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
public class MainView extends AppLayout {

    public MainView() {
        createHeader();
        createBody();
    }

    private void createHeader() {
        H1 logo = new H1("ProIT ToDo App");
        logo.addClassName("logo");

        Anchor logout = new Anchor("/logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void createBody() {
        RouterLink listLink = new RouterLink("Todo List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink,
                new RouterLink("Dashboard", DashboardView.class)
        ));
    }
}
