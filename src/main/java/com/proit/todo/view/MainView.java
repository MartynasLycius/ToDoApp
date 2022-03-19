package com.proit.todo.view;

import com.proit.todo.view.task.ListTaskView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Route("")
@StyleSheet("css/styles.css")
@StyleSheet("css/tailwind.min.css")
public class MainView extends VerticalLayout implements RouterLayout {

    public MainView() {
        add(new H1("To Do App"));
        UI.getCurrent().navigate(ListTaskView.class);
    }

}
