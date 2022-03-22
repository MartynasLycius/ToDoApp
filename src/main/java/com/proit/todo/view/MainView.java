package com.proit.todo.view;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

@StyleSheet("css/styles.css")
@StyleSheet("css/tailwind.min.css")
public class MainView extends VerticalLayout implements RouterLayout {

    public MainView() {
        H1 title = new H1("To Do App");
        title.setClassName("w-full text-5xl text-indigo-400 text-center font-bold");
        add(title);
    }

}
