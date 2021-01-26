package com.proit.todo.views.todo.list;

import com.proit.todo.data.dto.response.TodoResponse;
import com.proit.todo.data.service.contact.TodoService;
import com.proit.todo.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

@Route(value = "list", layout = MainView.class)
@PageTitle("List of Todo's")
@RouteAlias(value = "", layout = MainView.class)
public class ListOfTodoView extends Div {
    private final Grid<TodoResponse> todoGrid = new Grid<>(TodoResponse.class);
    private final TodoService todoService;
    private String todoId;


    public ListOfTodoView(TodoService todoService) {
        this.todoService = todoService;
        setId("listof-todos-view");
        add(this.configureGrid());
    }

    private Component configureGrid() {
        setId("grid");
        this.todoGrid.setWidth("100%");
        this.todoGrid.setColumns("todoTitle", "todoStatus", "dateCreated");
        configureEditBtn();
        configureDeleteBtn();

        /*
        * Populate Data from backend
        * */
        List<TodoResponse> tasks = todoService.getTodoList();
        this.todoGrid.setItems(tasks);
        return todoGrid;
    }

    /*
    * Making a Edit Btn with action
    * */
    private void configureEditBtn() {
        ValueProvider<TodoResponse, Button> edit = todo -> {
            Button editBtn = new Button("Edit");
            editBtn.addClickListener(e -> UI.getCurrent()
                    .navigate("update/" + todo.getTodoId())
            );
            return editBtn;
        };
        this.todoGrid.addComponentColumn(edit).setHeader("Edit");
    }

    /*
     * Making a Edit Btn with action
     * */
    private void configureDeleteBtn() {
        ValueProvider<TodoResponse, Button> todoResponseButtonValueProvider = todo -> {
            this.todoId = todo.getTodoId();
            Button deleteBtn = new Button("Delete");
            ConfirmDialog dialog = new ConfirmDialog("Confirm delete",
                    "Are you sure you want to delete the Todo?",
                    "Delete", this::onDelete, "Cancel", this::onCancel);
            dialog.setConfirmButtonTheme("error primary");
            deleteBtn.addClickListener(event -> dialog.open());

            return deleteBtn;
        };
        this.todoGrid.addComponentColumn(todoResponseButtonValueProvider).setHeader("Delete");
    }

    private void onDelete(ConfirmDialog.ConfirmEvent confirmEvent) {
        /*
        * Calling Deleting Action
        * */
        todoService.deleteTodoItem(this.todoId);
        this.configureGrid();
    }

    private void onCancel(ConfirmDialog.CancelEvent cancelEvent) {

    }


}
