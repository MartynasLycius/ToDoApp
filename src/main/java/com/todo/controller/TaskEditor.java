package com.todo.controller;

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
 *
 *  This editor will edit & delete tasks from task table using TaskRepository
 */
@SpringComponent
@UIScope
public class TaskEditor extends VerticalLayout implements KeyNotifier {


	private final TaskRepository repository;

	private Task task;

	TextField taskName = new TextField("Task Name");
	TextField taskDesc = new TextField("Task Description");

	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", VaadinIcon.TRASH.create());
	HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

	Binder<Task> binder = new Binder<>(Task.class);
	private ChangeHandler changeHandler;

	@Autowired
	public TaskEditor(TaskRepository repository) {
		this.repository = repository;

		add(taskName, taskDesc, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);

		save.getElement().getThemeList().add("primary");
		delete.getElement().getThemeList().add("error");

		addKeyPressListener(Key.ENTER, e -> save());

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		cancel.addClickListener(e -> {
			setVisible(false);
		});
		setVisible(false);
	}

	void delete() {
		repository.delete(task);
		changeHandler.onChange();
	}
	// saving data to task table
	void save() {
		repository.save(task);
		changeHandler.onChange();
	}

	/// changehandler interface to handle when some tasks or data is changed for a model
	public interface ChangeHandler {
		void onChange();
	}

	public final void editTask(Task c) {
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
