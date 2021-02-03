package com.todo.view;

import com.todo.controller.HistoryRepository;
import com.todo.controller.TaskRepository;
import com.todo.model.Task;
import com.todo.model.History;
import com.todo.controller.HistoryEditor;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

// this page will retrieve data from history table & show data in grid layout
@Route
public class HistoryPage extends VerticalLayout {

    private final HistoryRepository repo;
    final TextField filter;
    private final Grid<History> grid;
    private final HistoryEditor editor;

    public HistoryPage(MainView view)
    {
        repo = view.hist_repo;
        grid=new Grid<>(History.class);
        filter = new TextField();
        editor=view.hist_editor;

        view.add(filter,grid,editor);

        grid.setHeight("300px");
        grid.setColumns("taskName", "taskDesc","createDate","deleteDate");
        //grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        //grid.getColumnByKey("taskDesc").setAutoWidth(true);
        filter.setPlaceholder("Search Deleted task");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listTasks(e.getValue()));


        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editTask(e.getValue());
        });

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listTasks(filter.getValue());
        });

        // Initialize listing
        listTasks(null);

    }

    /// listing tasks from history i.e. HistoryRepository
    void listTasks(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findBytaskNameStartsWithIgnoreCase(filterText));
        }
    }
}
