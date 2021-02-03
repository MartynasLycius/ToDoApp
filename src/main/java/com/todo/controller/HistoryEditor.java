package com.todo.controller;

import com.todo.model.History;
import com.todo.model.Task;
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
 *  This editor will edit & restore tasks from history table to task table.
 */
@SpringComponent
@UIScope
public class HistoryEditor extends VerticalLayout implements KeyNotifier {

    private final HistoryRepository repository;
    private final TaskRepository taskRepository;
    private History task;

    TextField taskName = new TextField("Task Name");
    TextField taskDesc = new TextField("Task Description");
    Button save = new Button("Restore", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    HorizontalLayout actions = new HorizontalLayout(save, cancel);

    Binder<History> binder = new Binder<>(History.class);
    private ChangeHandler changeHandler;

    @Autowired
    public HistoryEditor(HistoryRepository repository, TaskRepository task_repo) {
        this.repository = repository;
        this.taskRepository=task_repo;
        add(taskName, taskDesc, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        /// restoring data
        save.addClickListener(e -> save());

        cancel.addClickListener(e -> {
            setVisible(false);
        });
        setVisible(false);
    }


    public interface ChangeHandler {
        void onChange();
    }

    /// restoring data to task table via taskRepo
    void save() {
        taskRepository.save(new Task(task.gettaskName(),task.gettaskDesc()));
        repository.delete(task); // deleting data from history table via historyRepo
        changeHandler.onChange(); // update the change via handler
    }

    public final void editTask(History c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            task = repository.findById(c.getId()).get();
        }
        else {
            task = c;
        }
        cancel.setVisible(persisted);
        binder.setBean(task);

        setVisible(true);

        // Focus first name initially
        taskName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }
}
