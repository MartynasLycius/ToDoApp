package com.todo.auth;

import com.todo.conf.SecurityUtils;
import com.todo.layout.HomeLayout;
import com.todo.utils.ExposedViews;
import com.todo.utils.LayoutUtils;
import com.todo.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@PageTitle("Login | Todo")
@Route(value = "login", layout = HomeLayout.class)
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    private static final Logger logger= LogManager.getLogger(LoginView.class);

    private final LoginForm login;

    public LoginView() {
        login = new LoginForm();
        LoginI18n loginForm = LoginI18n.createDefault();
        loginForm.getForm().setForgotPassword("Register New User");
        login.setI18n(loginForm);
        login.setAction("login");
        login.addForgotPasswordListener(event -> register());

        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(LayoutUtils.getBodyContainer(login));
    }

    private void register(){
        UI.getCurrent().navigate(Utils.getRelativePath(ExposedViews.REGISTRATION.getUri()));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // inform the user about an authentication error
        if (SecurityUtils.isUserLoggedIn()){
            event.rerouteTo("");
            return;
        }
        if(event.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
