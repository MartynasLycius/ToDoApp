package com.proit.todo.views.NotFound;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.proit.todo.views.main.MainView;
import com.vaadin.flow.router.RouterLink;

@Route(value = "404", layout = MainView.class)
@PageTitle("404 Not Found")
public class NotFoundView extends Div {

    public NotFoundView() {
        setId("not-found-view");
        add(new Label("Requested page not found. "));
        add(new Html("<a href='/task-list'>Click Here</a> "));
        add(new Label("  to go task list page."));

    }

}
