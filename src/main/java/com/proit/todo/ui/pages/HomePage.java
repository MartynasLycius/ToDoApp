package com.proit.todo.ui.pages;

import com.proit.todo.core.Form.task.TaskSearchForm;
import com.proit.todo.core.constant.Enums;
import com.proit.todo.core.persistence.entity.Task;
import com.proit.todo.core.service.iface.TaskService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route("")
public class HomePage extends VerticalLayout{
    private Grid<Task> taskGrid = new Grid<>(Task.class);
    private TaskService taskService;
    private TextField searchByNameField = new TextField();
    private RadioButtonGroup<String> searchByStateBtn = new RadioButtonGroup<>();
    private TaskSearchForm taskSearchForm = new TaskSearchForm();
    private Button createNewTaskNavBtn = new Button();
    private List<Task> tasks = new ArrayList<>();

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
        this.configureNavBtn();

        /**
         * Default Data Fetch
         * */
        this.fetchTaskAll( );

        /**
         * Layout manipulation
         * */
        super.addClassName("list-view");
        super.setSizeFull();
        super.add(this.createNewTaskNavBtn,
                this.searchByNameField,
                    this.searchByStateBtn,
                    this.taskGrid);
    }
    private void fetchTaskByState(Enums.TASK_STATE state){
        this.taskSearchForm.setState(state);

        this.tasks = this.taskService.getAllBySearchCriteriaNoPagination(taskSearchForm);
        this.taskGrid.setItems(tasks);
    }
    private void fetchTaskByName(String name){
        this.taskSearchForm.setName(name);

        this.tasks = this.taskService.getAllBySearchCriteriaNoPagination(taskSearchForm);
        this.taskGrid.setItems(tasks);
    }
    private void fetchTaskAll( ){
        this.taskSearchForm.setState(null);

        this.tasks =  this.taskService.getAllBySearchCriteriaNoPagination(taskSearchForm);
        this.taskGrid.setItems(tasks);
    }

    private void configureGrid(){



        this.taskGrid.setColumns("name","state","createdDate");

        this.taskGrid.addComponentColumn(task->{
            Button editBtn =  new Button("Edit");
            editBtn.addClickListener(e-> UI.getCurrent()
                    .navigate("edit-task/"+task.getId())
            );

            return editBtn;
        });

        this.taskGrid.addComponentColumn(task->{

            Checkbox changeStateBtn =  new Checkbox();
            boolean checkValue = task.getState().equals(Enums.TASK_STATE.DONE);
            changeStateBtn.setValue(checkValue);

            changeStateBtn.addClickListener(e-> {

                boolean isChecked = changeStateBtn.getValue();
                Enums.TASK_STATE newState = isChecked?  Enums.TASK_STATE.DONE:Enums.TASK_STATE.NEW;

                /**
                 * Update State
                 * */
                Task updatedTask = this.taskService.updateState(task.getId(),newState);

                /**
                 * UI elements grid row
                 * */
                this.refreshGrid(updatedTask);

                }
            );


            return changeStateBtn;

        });

    }
    private void configureNavBtn(){
        this.createNewTaskNavBtn.setText("Create task");

        /**
         * Event Register
         * */
        this.createNewTaskNavBtn.addClickListener(e-> UI.getCurrent()
                                                        .navigate("create-task")
                                                    );
    }
    private void refreshGrid(Task task){
        this.tasks = this.tasks.stream()
                                .map(t->{
                                            if(t.getId() == task.getId())
                                                return task;
                                            return t;
                                }).collect(Collectors.toList());


        this.taskGrid.setItems(this.tasks);
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
