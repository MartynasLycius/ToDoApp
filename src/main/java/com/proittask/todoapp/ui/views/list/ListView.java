package com.proittask.todoapp.ui.views.list;

import com.proittask.todoapp.backend.entity.Item;
import com.proittask.todoapp.backend.service.ToDoService;
import com.proittask.todoapp.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Route(value = "", layout = MainView.class)
@PageTitle("Todo Lists | ToDo App")
public class ListView extends VerticalLayout {
    TodoForm form;
    Grid<Item> grid = new Grid<>(Item.class);
    TextField filterText = new TextField();
    ToDoService toDoService;

    public ListView(ToDoService toDoService) {
        this.toDoService = toDoService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new TodoForm(toDoService.findAll());
        form.addListener(TodoForm.SaveEvent.class, this::saveItem);
        form.addListener(TodoForm.DeleteEvent.class, this::deleteItem);
        form.addListener(TodoForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteItem(TodoForm.DeleteEvent evt) {
        toDoService.delete(evt.getItem());
        updateList();
        closeEditor();
    }

    private void saveItem(TodoForm.SaveEvent evt) {
        toDoService.save(evt.getItem());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addItemButton = new Button("Add New Item", click -> addItem());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addItemButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addItem() {
        grid.asSingleSelect().clear();
        editItem(new Item());
    }

    private void configureGrid() {
        grid.addClassName("item-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("name");
        grid.setColumns("name", "description", "targetDate", "status");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editItem(evt.getValue()));
    }

    private void editItem(Item item) {
        if (item == null) {
            closeEditor();
        } else {
            form.setItem(item);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setItem(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(toDoService.findAll(filterText.getValue()));
    }

}
