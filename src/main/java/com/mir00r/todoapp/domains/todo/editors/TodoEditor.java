package com.mir00r.todoapp.domains.todo.editors;

import com.mir00r.todoapp.domains.todo.models.entities.ToDoItem;
import com.mir00r.todoapp.domains.todo.repositories.ToDoItemRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mir00r on 30/1/21
 * @project IntelliJ IDEA
 */
@SpringComponent
@UIScope
public class TodoEditor extends VerticalLayout implements KeyNotifier {

    private final ToDoItemRepository toDoItemRepository;

    private ToDoItem entity;

    TextField name = new TextField("Name");
    TextField description = new TextField("Description");
//    Date description = new TextField("Description");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<ToDoItem> binder = new Binder<>(ToDoItem.class);
    private ChangeHandler changeHandler;

    @Autowired
    public TodoEditor(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;

        add(name, description, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> update(entity));
        setVisible(false);
    }

    void delete() {
        this.toDoItemRepository.delete(entity);
        changeHandler.onChange();
    }

    void save() {
        this.toDoItemRepository.save(entity);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void update(ToDoItem c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            entity = this.toDoItemRepository.find(c.getId()).get();
        } else {
            entity = c;
        }

        cancel.setVisible(persisted);
        binder.setBean(entity);
        setVisible(true);
        name.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}
