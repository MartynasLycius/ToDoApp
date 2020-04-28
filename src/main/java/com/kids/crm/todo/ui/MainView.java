package com.kids.crm.todo.ui;

import com.kids.crm.todo.ui.component.ComponentFactory;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

import javax.annotation.PostConstruct;

/**
 * The main view contains a simple label element and a template element.
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow with CDI", shortName = "Project Base")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {


    @PostConstruct
    public void init() {

        setWidth("590px");
        setClassName("auto-margin");
        add(ComponentFactory.createHeader("Dashboard"),
                new RouterLink("Todo List", TodoList.class),
                new RouterLink("Add Todo", TodoAddForm.class)
                );
    }

}
