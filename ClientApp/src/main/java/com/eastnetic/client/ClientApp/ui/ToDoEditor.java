package com.eastnetic.client.ClientApp.ui;

import com.eastnetic.client.ClientApp.model.ToDo;
import com.eastnetic.client.ClientApp.repo.ToDoRepository;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@SpringComponent
@UIScope
public class ToDoEditor extends VerticalLayout implements View{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ToDoRepository toDoRepository;
	
	public ToDo todo;
	
	/* Fields to edit properties in todo entity */
	public TextField firstName = new TextField("First name");
	public TextField lastName = new TextField("Last name");
	public TextField phone = new TextField("Phone");
	public TextField eMail = new TextField("E mail");
	//DateField birthDate = new DateField("Birth date");
	
	/* Action buttons */
	public Button save = new Button("Save", FontAwesome.SAVE);
	public Button cancel = new Button("Cancel");
	public Button delete = new Button("Delete", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<ToDo> binder = new Binder<ToDo>(ToDo.class);
	
	@Autowired
	public ToDoEditor(ToDoRepository repository) {
		this.toDoRepository = repository;
		addComponents(firstName, lastName, phone, eMail, actions);
		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void edittodo(ToDo s) {
		if (s == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = s.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			todo = toDoRepository.getOne(s.getId());
		}
		else {
			todo = s;
		}
		cancel.setVisible(persisted);

		// Bind todo properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(todo);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		firstName.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
