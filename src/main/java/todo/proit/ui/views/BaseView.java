package todo.proit.ui.views;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ErrorLevel;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dipanjal
 * @since 2/20/2021
 */
public abstract class BaseView extends VerticalLayout {

    protected final String THEME_FOR_TEXT_FIELD = "bordered";
    private final Binder binder = new Binder();

    protected void configureTextField(TextField textField, String errorMessage){
        textField.setSizeFull();
        textField.setClearButtonVisible(true);
        textField.setThemeName(THEME_FOR_TEXT_FIELD);
        textField.setRequired(true);
        binder.forField(textField)
                .withValidator(
                        v -> StringUtils.isNotBlank(textField.getValue()),
                        errorMessage,
                        ErrorLevel.ERROR
                );
    }

    protected void configureTextArea(TextArea textArea){
        textArea.setSizeFull();
        textArea.setClearButtonVisible(true);
        textArea.setThemeName(THEME_FOR_TEXT_FIELD);
        textArea.setRequired(true);
    }

    protected Notification showNotification(String text) {
        int duration = 5000;
        Notification notification = new Notification(text, duration, Notification.Position.BOTTOM_CENTER);
        notification.open();
        return notification;
    }


}
