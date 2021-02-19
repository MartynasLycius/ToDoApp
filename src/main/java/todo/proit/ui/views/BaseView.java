package todo.proit.ui.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.PWA;

/**
 * @author dipanjal
 * @since 2/20/2021
 */
@PWA(name = "Todo App",
        shortName = "proIT Todo App",
        description = "Todo Application for proIT",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public abstract class BaseView extends VerticalLayout {

    protected final String THEME_FOR_TEXT_FIELD = "bordered";

    protected void configureTextField(TextField textField){
        textField.setSizeFull();
        textField.setClearButtonVisible(true);
        textField.setThemeName(THEME_FOR_TEXT_FIELD);
    }

    protected void configureTextArea(TextArea textArea){
        textArea.setSizeFull();
        textArea.setClearButtonVisible(true);
        textArea.setThemeName(THEME_FOR_TEXT_FIELD);
    }
}
