package com.mir00r.todoapp;

import com.mir00r.todoapp.domains.todo.editors.TodoEditor;
import com.mir00r.todoapp.domains.todo.models.entities.ToDoItem;
import com.mir00r.todoapp.domains.todo.repositories.ToDoItemRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author mir00r on 3/2/21
 * @project IntelliJ IDEA
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private final ToDoItemRepository toDoItemRepository;
    private final TodoEditor todoEditor;
    private final Grid<ToDoItem> toDoItemGrid;
    private final TextField filter;
    private final Button addNewBtn;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param toDoItemRepository
     * @param todoEditor
     */
    public MainView(@Autowired ToDoItemRepository toDoItemRepository, TodoEditor todoEditor) {
        this.toDoItemRepository = toDoItemRepository;
        this.todoEditor = todoEditor;
        this.toDoItemGrid = new Grid<>(ToDoItem.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New", VaadinIcon.PLUS.create());

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");
        initToDoUI(todoEditor, toDoItemGrid, filter, addNewBtn);
    }

    private void initToDoUI(TodoEditor editor, Grid<ToDoItem> grid, TextField filter, Button addNewBtn) {
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("400px");
        grid.setWidth("800px");

        grid.setColumns("id", "name", "description");
        grid.getColumnByKey("id").setWidth("100px").setFlexGrow(1);

        filter.setPlaceholder("Filter by name");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> toDoList(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> editor.update(e.getValue()));

        addNewBtn.addClickListener(e -> editor.update(new ToDoItem("", "")));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            toDoList(filter.getValue());
        });

        toDoList(null);
    }

    void toDoList(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            toDoItemGrid.setItems(this.toDoItemRepository.findAll());
        } else {
            toDoItemGrid.setItems(this.toDoItemRepository.search(filterText));
        }
    }

}
