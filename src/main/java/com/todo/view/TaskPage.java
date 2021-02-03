package com.todo.view;

import com.todo.controller.TaskEditor;
import com.todo.controller.TaskRepository;
import com.todo.model.Task;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

// This page will show tasks from toDoList
@Route
public class TaskPage extends VerticalLayout {

    private final TaskRepository repo;
    final TextField filter;
    private final Button addNewBtn;
    private final Grid<Task> grid;
    private TaskEditor editor; // where each grid task can me edited & modified
    public TaskPage(MainView view)
    {
        repo = view.task_repo;
        grid=new Grid<>(Task.class);
        filter = new TextField();
        addNewBtn = new Button("New Task", VaadinIcon.PLUS.create());
        editor=view.task_editor;

        view.add(filter,grid,addNewBtn,editor);
        grid.setHeight("300px");

        /// setting columns to grid
        grid.setColumns("taskName","taskDesc","createDate");
        grid.getColumnByKey("taskDesc").setAutoWidth(true);
        grid.getColumnByKey("taskName").setAutoWidth(true);
        grid.getColumnByKey("createDate").setAutoWidth(true);
        filter.setPlaceholder("Search your task");

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listTasks(e.getValue()));

        /// when a task is selected to edit
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editTask(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editTask(new Task("", "")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listTasks(filter.getValue());
        });

        // Initialize listing
        listTasks(null);

    }

    /// listing task from TaskRepository
    void listTasks(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findBytaskNameStartsWithIgnoreCase(filterText));
        }
    }
}
