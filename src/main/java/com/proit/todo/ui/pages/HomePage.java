package com.proit.todo.ui.pages;

import com.proit.todo.core.Form.task.TaskSearchForm;
import com.proit.todo.core.constant.Enums;
import com.proit.todo.core.persistence.entity.Task;
import com.proit.todo.core.service.iface.TaskService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class HomePage extends VerticalLayout{
    private Grid<Task> taskGrid = new Grid<>(Task.class);
    private TaskService taskService;
    private TextField searchByNameField = new TextField();
    private RadioButtonGroup<String> searchByStateBtn = new RadioButtonGroup<>();
    private TaskSearchForm taskSearchForm = new TaskSearchForm();


    public HomePage(TaskService taskService) {
        /**
         * Dependency injected by spring
         * */
        this.taskService = taskService;



        /**
         * Configuration methods
         * */
        this.configureSearchFields();
        this.configureGrid();

        /**
         * Default Data Fetch
         * */
        this.fetchTaskAll( );

        /**
         * Layout manipulation
         * */
        super.addClassName("list-view");
        super.setSizeFull();
        super.add(this.searchByNameField,
                    this.searchByStateBtn,
                    this.taskGrid);
    }
    private void fetchTaskByState(Enums.TASK_STATE state){
        this.taskSearchForm.setState(state);

        List<Task> tasks = this.taskService.getAllBySearchCriteriaNoPagination(taskSearchForm);
        this.taskGrid.setItems(tasks);
    }
    private void fetchTaskByName(String name){
        this.taskSearchForm.setName(name);

        List<Task> tasks = this.taskService.getAllBySearchCriteriaNoPagination(taskSearchForm);
        this.taskGrid.setItems(tasks);
    }
    private void fetchTaskAll( ){
        this.taskSearchForm.setState(null);

        List<Task> tasks =  this.taskService.getAllBySearchCriteriaNoPagination(taskSearchForm);
        this.taskGrid.setItems(tasks);
    }

    private void configureGrid(){

        this.taskGrid.setColumns("name","state","createdDate");
    }

    private void configureSearchFields(){

        this.searchByStateBtn.setItems("All", "New", "Completed");
        this.searchByStateBtn.setValue("All");

        this.searchByNameField.setPlaceholder("Search... ");
        this.searchByNameField.setClearButtonVisible(true);
        this.searchByNameField.setValueChangeMode(ValueChangeMode.LAZY);

        /**
         * Event Register
         * */
        this.searchByStateBtn.addValueChangeListener(cl->this.searchByStateBanValueChangeListener(cl.getValue()));
        this.searchByNameField.addValueChangeListener(e-> this.fetchTaskByName(e.getValue()));

    }
    private void searchByStateBanValueChangeListener(String value){
        switch (value){
            case "All":
                this.fetchTaskAll();
                break;

            case "New":
                this.fetchTaskByState(Enums.TASK_STATE.NEW);
                break;

            case "Completed":
                this.fetchTaskByState(Enums.TASK_STATE.DONE);

        }
    }



}
