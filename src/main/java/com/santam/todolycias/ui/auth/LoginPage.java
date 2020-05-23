package com.santam.todolycias.ui.auth;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route("login")
@PageTitle("Login | ProIT ToDo App")
public class LoginPage extends VerticalLayout implements BeforeEnterObserver {

    LoginOverlay component = new LoginOverlay();
    public LoginPage(){


        H3 title = new H3();
        title.getStyle().set("color", "var(--lumo-base-color)");
        Icon icon = VaadinIcon.SIGN_IN.create();
        icon.setSize("30px");
        icon.getStyle().set("top", "-4px");
        title.add(icon);
        title.add(new Text(" ToDo App"));
        component.setTitle(title);
        component.setDescription("ProIT ToDo App Login Form");
        component.setAction("login");
        component.setOpened(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(!beforeEnterEvent
                .getLocation()
                .getQueryParameters()
                .getParameters()
                .getOrDefault("error", Collections.emptyList())
                .isEmpty()){
            component.setError(true);
        }
    }
}
