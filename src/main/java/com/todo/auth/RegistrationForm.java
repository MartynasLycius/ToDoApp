package com.todo.auth;

import com.todo.conf.SecurityUtils;
import com.todo.user.entity.User;
import com.todo.user.service.UserSerive;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.shared.Registration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.hibernate.validator.constraints.UniqueElements;

public class RegistrationForm extends FormLayout {
    private static final Logger logger= LogManager.getLogger(RegistrationForm.class);

    private UserSerive userSerive;

    @Required
    @PropertyId("fullName")
    private final TextField fullName;

    @PropertyId("phone")
    private final TextField phone;

    @Required
    @PropertyId("email")
    private final EmailField email;

    @PropertyId("address")
    private final TextField address;

    @Required
    @PropertyId("username")
    private final TextField username;

    @Required
    @PropertyId("password")
    private final PasswordField password;

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");
    private final Binder<User> binder;

    public RegistrationForm(UserSerive userSerive){
        boolean editAction=false;
        this.userSerive = userSerive;
        addClassName("registration-form");

        fullName = new TextField("Full Name");
        phone = new TextField("Contact No.");
        email = new EmailField("Email");
        address= new TextField("Address");
        username= new TextField("User Name");
        password= new PasswordField("Password");

        binder = new BeanValidationBinder<>(User.class);

        String loggedUserName= SecurityUtils.getLoggedUserName();
        logger.debug("LoggedUserName: "+loggedUserName);
        if (loggedUserName==null){
            binder.setBean(new User());
        }else {
            editAction=true;
            binder.setBean(userSerive.loadUserByUserName(loggedUserName));
            password.setVisible(false);
            password.setEnabled(false);
            username.setEnabled(false);
            email.setEnabled(false);
        }

        binder.bindInstanceFields(this);

        binder.forMemberField(username).withValidator(
                v -> binder.getBean().isPersisted()
                        || userSerive.findAllAsStream().noneMatch(u -> u.getUsername().equals((v.trim()))),
                "Already taken, please try with another one!"
        ).bind(User::getUsername, User::setUsername).validate();

        binder.forMemberField(email).withValidator(
                v -> binder.getBean().isPersisted()
                        ||userSerive.findAllAsStream().noneMatch(u -> u.getEmail().equals((v.trim()))),
                "Email already used"
        ).bind(User::getEmail, User::setEmail).validate();

        H3 formTitle= new H3("User Registration Form: ");
        formTitle.setWidthFull();

        HorizontalLayout titleLayout= new HorizontalLayout();
        titleLayout.setClassName("form-title");
        titleLayout.setSpacing(false);
        titleLayout.setWidthFull();
        titleLayout.add(formTitle);

        add(
                titleLayout,
                fullName,
                phone,
                email,
                address,
                username,
                password,
                createButtonsLayout());
    }

    public void setUser(User user) {
        binder.setBean(user);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CancelEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        HorizontalLayout btnLayout= new HorizontalLayout();
        btnLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        btnLayout.addClassName("form-btn-row");
        btnLayout.setSpacing(false);
        btnLayout.setWidthFull();
        btnLayout.add(save, close);

        return btnLayout;
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events
    public static abstract class RegistrationFormEvent extends ComponentEvent<RegistrationForm> {
        private User user;

        protected RegistrationFormEvent(RegistrationForm source, User user) {
            super(source, false);
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    public static class SaveEvent extends RegistrationFormEvent {
        SaveEvent(RegistrationForm source, User user) {
            super(source, user);
            //logger.debug("user: "+user);
        }
    }

    public static class DeleteEvent extends RegistrationFormEvent {
        DeleteEvent(RegistrationForm source, User user) {
            super(source, user);
        }

    }

    public static class CancelEvent extends RegistrationFormEvent {
        CancelEvent(RegistrationForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}