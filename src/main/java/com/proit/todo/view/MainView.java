package com.proit.todo.view;

import com.proit.todo.model.Task;
import com.proit.todo.repository.TaskRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.Pageable;

@Route
public class MainView extends VerticalLayout {

    private final TaskRepository repository;
    final Grid<Task> grid;
    TextField filter;

    public MainView(TaskRepository repository) {
        this.repository = repository;
        this.grid = new Grid<>(Task.class);
        this.filter = new TextField();

        HorizontalLayout actions = new HorizontalLayout(filter);
        filter.setPlaceholder("Filter by task name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listAllTask(e.getValue()));

        add(actions, grid);

        listAllTask();
    }

    private void listAllTask() {
        grid.setItems(repository.findAll());
    }

    private void listAllTask(String filterTxt) {
        grid.setItems(repository.findAllByNameContainingIgnoreCase(filterTxt, Pageable.unpaged()).getContent());
    }
}
