/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.editor.view;

import com.nazmul.todo.constant.CategoryType;
import com.nazmul.todo.constant.ViewIdentifires;
import com.nazmul.todo.domain.Category;
import com.nazmul.todo.domain.TodoItem;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.HasValue;
import com.vaadin.data.ValidationException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ErrorLevel;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.themes.ValoTheme;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MCheckBox;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 *
 * @author nazmul
 */
@CDIView(ViewIdentifires.EDITOR_VIEW)
public class TodoEditorView  extends MVerticalLayout implements View {

    @Inject
    private ITodoEditorPresenter presenter;
    
    private final MTextField name  = new MTextField("Name").withWidth("50%");
    private final TextArea description = new TextArea("Description");
    private final DateField date = new DateField("Date");
    private final MCheckBox done = new MCheckBox("Done")
            .withStyleName(ValoTheme.CHECKBOX_LARGE).withFullWidth();
    
    private final ComboBox<Category> type = new ComboBox<>("Type"); 
    
    private BeanValidationBinder<TodoItem> binder = new BeanValidationBinder<>(TodoItem.class);

    public void initComponent(String itemId) {
        
        this.setSizeFull();
        this.setSpacing(true);
        this.setMargin(false);
        
        description.setWidth("100%");
        name.setWidth("50%");
        type.addStyleName(ValoTheme.COMBOBOX_LARGE);
        date.addStyleName(ValoTheme.DATEFIELD_LARGE);
        FormLayout editorForm = generateEditorForm();
        add(editorForm, Alignment.MIDDLE_CENTER);
        
        bindData(itemId);
    }

    private FormLayout generateEditorForm() {
        FormLayout editorForm = new FormLayout();
        editorForm.addStyleName(ValoTheme.FORMLAYOUT_LIGHT 
                + " " + "shadowLayout"
                + " " + "todo-editor-backgroud");
        editorForm.setWidth("70%");
        editorForm.setSpacing(true);
        editorForm.setMargin(new MarginInfo(false, true, false, true));
        
        setTypesItems();
        
        MLabel details = generateFormSectionSeparatorLabel("Details");
        MLabel category = generateFormSectionSeparatorLabel("Category");
        
        editorForm.addComponents(details, name, description,
                date, done, category, type);
        generateSaveCancelButton(editorForm);
        
        return editorForm;
    }
    
    private MLabel generateFormSectionSeparatorLabel(String content) {
        MLabel label = new MLabel(content)
                .withStyleName(ValoTheme.LABEL_H2 + " " + ValoTheme.LABEL_COLORED);
        
        return label;
    }

    private void generateSaveCancelButton(FormLayout editorForm) {
        MHorizontalLayout mh = new MHorizontalLayout()
                .withSpacing(true)
                .withMargin(new MarginInfo(true, false, true, false));
        
        MButton saveButton = new MButton("To do")
                .withIcon(VaadinIcons.CHECK)
                .withListener((event) -> {
                    validate();
                });
        
        MButton cancelButton = new MButton("Not to do")
                .withIcon(VaadinIcons.CLOSE)
                .withStyleName(ValoTheme.BUTTON_QUIET)
                .withListener((event) -> {
                    navigateTo();
                });
        
        mh.add(cancelButton, saveButton);
        
        editorForm.addComponent(mh);
    }
    
    private void bindData(String itemId) {
        
        binder.bind(name, TodoItem::getName, TodoItem::setName);
        
        binder.bind(description, TodoItem::getDescription, TodoItem::setDescription);

        binder.bind(done, TodoItem::getDone, TodoItem::setDone);
        
        binder.forField(type)
                .asRequired("Please select a type")
                .withValidator((t) -> t != null,
                "Please select type.",
                ErrorLevel.ERROR)
                .bind(TodoItem::getCategory, TodoItem::setCategory);
        
        binder.forField(date)
                .asRequired("Please select a date")
                .withValidator((t) -> t != null,
                "Please select date.",
                ErrorLevel.ERROR)
                .bind(TodoItem::getTimecreated, TodoItem::setTimecreated);
        
        binder.setBean(presenter.getToDoItem(itemId));
    }
    
    private void navigateTo() {
        presenter.navigateTo(ViewIdentifires.TODOS_VIEW);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        initComponent(event.getParameters());
    }

    private void setTypesItems() {
        type.setItems(presenter.getCategories());
        
        type.setItemCaptionGenerator((item) -> {
            return CategoryType.findById(item.getId()).getKey().toUpperCase(); //To change body of generated lambdas, choose Tools | Templates.
        });
    }

    private void validate() {
        try {
            binder.writeBean(presenter.getToDoItem());
            presenter.saveItem(presenter.getToDoItem());
            navigateTo();
        } catch (ValidationException ex) {
            Logger.getLogger(TodoEditorView.class.getName())
                    .log(Level.WARNING, "Validation failed for the todo item: " + binder.getBean(), ex);
            Notification.show("Please enter all required informatiom");
        }
    }

}
