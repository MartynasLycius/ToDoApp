package com.todo.auth;


import com.todo.conf.SecurityUtils;
import com.todo.layout.HomeLayout;
import com.todo.user.entity.User;
import com.todo.user.service.UserSerive;

import com.todo.utils.ExposedViews;
import com.todo.utils.LayoutUtils;
import com.todo.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Registration | Todo")
@Route(value = "registration", layout = HomeLayout.class)
public class RegistrationView extends VerticalLayout implements BeforeEnterObserver {
    private static final Logger logger= LogManager.getLogger(RegistrationView.class);

    private UserSerive userSerive;

    private RegistrationForm form;

    public RegistrationView(@Autowired UserSerive userSerive){
        this.userSerive=userSerive;
        addClassName("registration-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        form = new RegistrationForm(userSerive);
        form.addListener(RegistrationForm.SaveEvent.class, this::saveUser);
        form.addListener(RegistrationForm.DeleteEvent.class, this::deleteUser);
        form.addListener(RegistrationForm.CancelEvent.class, e -> cancelRegistration());

        add(LayoutUtils.getBodyContainer(form));
    }

    private void deleteUser(RegistrationForm.DeleteEvent evt) {
        userSerive.deleteUser(evt.getUser());
    }

    private void saveUser(RegistrationForm.SaveEvent evt) {
        //logger.debug("saving User");
        userSerive.registerUser(evt.getUser());
        Notification notification = new Notification(
                "You have successfully registered", 3000,
                Notification.Position.TOP_CENTER);
        notification.open();
        cancelRegistration();
    }

    private void updateUser(RegistrationForm.SaveEvent evt) {
        userSerive.registerUser(evt.getUser());
    }

    private void editUser(User user) {
        if (user == null) {
            cancelRegistration();
        } else {
            form.setUser(user);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void cancelRegistration() {
        if (SecurityUtils.isUserLoggedIn()){
            UI.getCurrent().navigate("");
        }else {
            UI.getCurrent().navigate(Utils.getRelativePath(ExposedViews.LOGIN.getUri()));
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        /*if (SecurityUtils.isUserLoggedIn()){
            event.rerouteTo("");
        }*/
    }
}
