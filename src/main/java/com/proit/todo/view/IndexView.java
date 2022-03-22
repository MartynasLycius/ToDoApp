package com.proit.todo.view;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainView.class)
public class IndexView extends VerticalLayout {

    public IndexView() {
        Anchor anchor = new Anchor("tasks", "Goto Task List");
        add(anchor);
    }
}
