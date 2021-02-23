package todo.proit.ui.views;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import todo.proit.common.model.request.task.TaskRequest;

/**
 * @author dipanjal
 * @since 2/20/2021
 */
public abstract class BaseView extends VerticalLayout {

    private final String THEME_FOR_TEXT_FIELD = "bordered";

    /** @Todo Need to reade from property file */
    protected final String SUBMIT_BUTTON_VALUE = "Save";
    protected final String NAV_BUTTON_VALUE = "Back";

    protected final String ERR_NAME_REQUIRED = "Name cannot be empty";
    protected final String ERR_DESC_REQUIRED = "Description cannot be empty";


    protected void configureTextField(TextField textField){
        textField.setSizeFull();
        textField.setClearButtonVisible(true);
        textField.setThemeName(THEME_FOR_TEXT_FIELD);
        textField.setRequired(true);
    }

    protected void configureTextArea(TextArea textArea){
        textArea.setSizeFull();
        textArea.setClearButtonVisible(true);
        textArea.setThemeName(THEME_FOR_TEXT_FIELD);
        textArea.setRequired(true);
    }


    protected Notification showNotification(String message) {
        int duration = 5000;
        Notification notification = new Notification(message, duration, Notification.Position.BOTTOM_START);
        notification.open();
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        return notification;
    }

    protected void configureNavBtn(Button button){
        button.setText(NAV_BUTTON_VALUE);
        button.setWidthFull();
        button.setIcon(VaadinIcon.ARROW_BACKWARD.create());
        button.addClickShortcut(Key.ESCAPE);
        button.addClickListener(event ->
                UI.getCurrent()
                        .navigate("")
        );
    }

    protected void configureSubmitButton(Button button){
        button.setText(SUBMIT_BUTTON_VALUE);
        button.setWidthFull();
        button.setIcon(VaadinIcon.CHECK_CIRCLE_O.create());
        button.addClickShortcut(Key.ENTER, KeyModifier.CONTROL);
    }


}
