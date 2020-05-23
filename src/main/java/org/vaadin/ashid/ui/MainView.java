package org.vaadin.ashid.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.ashid.model.ToDo;
import org.vaadin.ashid.service.ToDoServiceInf;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
/*
* @author  Md Ahasanul Ashid
* @version 1.0
* @email:  ashid8bd@gmail.com 
*/
@Route("")
@PWA(name = "ToDo Application", shortName = "ToDoApp", description = "This is a Vaadin ToDo Application.", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	public MainView(@Autowired ToDoServiceInf service) {
		setSizeFull();
		H1 h1 = new H1("ToDo Item List");
		setHorizontalComponentAlignment(Alignment.CENTER, h1);
		
		ListDataProvider<ToDo> dataProvider = service.findAll();
		Crud<ToDo> crud = new Crud<>(ToDo.class, createGrid(), createToDoEditor());
		crud.setMaxWidth("800px");
		crud.setWidth("100%");
		crud.setDataProvider(dataProvider);
		setHorizontalComponentAlignment(Alignment.CENTER, crud);

		CrudI18n customI18n = CrudI18n.createDefault();
		customI18n.setEditItem("Edit ToDo Item");
		customI18n.setNewItem("Add ToDo Item");
		crud.setI18n(customI18n);

		crud.addSaveListener(saveEvent -> {
			ToDo toSave = saveEvent.getItem();
			// Save the item in the database
			service.save(toSave);
			if (!dataProvider.getItems().contains(toSave)) {
				dataProvider.getItems().add(toSave);
			}
		});

		crud.addDeleteListener(deleteEvent -> {
			ToDo toDelete = deleteEvent.getItem();
			// Delete the item in the database
			service.delete(toDelete);
			dataProvider.getItems().remove(deleteEvent.getItem());

		});

		add(h1, crud);
	}

	private Grid<ToDo> createGrid() {
		Grid<ToDo> grid = new Grid<>();
		grid.addColumn(c -> c.getDate()).setHeader("Date").setWidth("160px");
		grid.addColumn(c -> c.getItemName()).setHeader("Item Name");
		grid.addColumn(c -> c.getDescription()).setHeader("Item Description");
		Crud.addEditColumn(grid);
		return grid;
	}

	private CrudEditor<ToDo> createToDoEditor() {

		DatePicker date = new DatePicker("Date");
		date.setRequiredIndicatorVisible(true);
		setColspan(date, 4);

		TextField itemName = new TextField("Item Name");
		itemName.setRequiredIndicatorVisible(true);
		setColspan(itemName, 4);

		TextField description = new TextField("Item Description");
		description.setRequiredIndicatorVisible(true);
		setColspan(description, 4);

		FormLayout form = new FormLayout(date, itemName, description);
		form.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 4));

		Binder<ToDo> binder = new Binder<>(ToDo.class);
		binder.bind(date, ToDo::getDate, ToDo::setDate);
		binder.bind(itemName, ToDo::getItemName, ToDo::setItemName);
		binder.bind(description, ToDo::getDescription, ToDo::setDescription);
		return new BinderCrudEditor<>(binder, form);
	}

	private void setColspan(Component component, int colspan) {
		component.getElement().setAttribute("colspan", Integer.toString(colspan));
	}

}
