package com.proit.todo.views.task;

import com.proit.todo.data.entity.Task;
import com.proit.todo.data.service.TaskService;
import com.proit.todo.views.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;


@Route(value = "task-list", layout = MainView.class)
@PageTitle("List")
@CssImport(value = "./styles/views/list/list-view.css", include="lumo-badge")
@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class TaskListView extends Div {

    private Grid<Task> grid;

    private TaskService taskService;

    public TaskListView(@Autowired TaskService taskService) {
        setId("master-detail-view");
        this.taskService = taskService;

        // Configure Grid
        createGridComponent();
        add(grid);

    }

    private void createGridComponent() {

        grid = new Grid<>(Task.class);
        grid.setColumns("name", "description", "taskDate");
        grid.addComponentColumn(task->{
            Button editBtn =  new Button("Edit");
            editBtn.addClickListener(e-> UI.getCurrent()
                    .navigate("edit-task/"+task.getId())
            );

            return editBtn;
        });

        grid.setDataProvider(new CrudServiceDataProvider<Task, Void>(taskService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

    }


};
