package com.proit.todo.ui.pages;

import com.proit.todo.core.persistence.entity.Task;
import com.proit.todo.core.service.iface.TaskService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;


@Route("edit-task")
public class EditTaskPage extends VerticalLayout implements HasUrlParameter<Integer> {

    private TaskService taskService;
    private TextField nameTextField = new TextField();
    private TextArea descriptionTextField = new TextArea();
    private Button submitBtn = new Button();
    private Button homeNavBtn = new Button();


    public EditTaskPage(TaskService taskService) {
        /**
         * Dependency injected by spring
         * */
        this.taskService = taskService;

        /**
         * Configuration methods
         * */
        this.configureFormFields();
        this.configureNavBtn();

        /**
         * Layout manipulation
         * */
        super.addClassName("list-view");
        super.setSizeFull();
        super.add(this.homeNavBtn,
                this.nameTextField,
                this.descriptionTextField,
                this.submitBtn);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer id) {

        this.setTaskData(id);
    }
    private void setTaskData(Integer id){

        Task task = this.taskService.getById(id,true);
        this.nameTextField.setValue(task.getName());
        this.descriptionTextField.setValue(task.getDescription());
    }
    private void configureFormFields(){
        this.nameTextField.setPlaceholder("Name");
        this.descriptionTextField.setPlaceholder("Description");
        this.submitBtn.setText("Save");
    }

    private void configureNavBtn(){
        this.homeNavBtn.setText("Go Home");

        /**
         * Event Register
         * */
        this.homeNavBtn.addClickListener(e-> UI.getCurrent()
                .navigate("")
        );
    }
}
