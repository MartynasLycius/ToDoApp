package com.proit.todo.ui.pages;

import com.proit.todo.core.Form.task.TaskCreateForm;
import com.proit.todo.core.service.iface.TaskService;
import com.proit.todo.ui.helper.TaskFormValidation;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;


@Route("create-task")
public class CreateTaskPage extends VerticalLayout {

    private TaskService taskService;
    private TextField nameTextField = new TextField();
    private TextArea descriptionTextField = new TextArea();
    private Button submitBtn = new Button();
    private Button homeNavBtn = new Button();


    public CreateTaskPage(TaskService taskService) {
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

    private void configureFormFields(){
        this.nameTextField.setPlaceholder("Name");
        this.descriptionTextField.setPlaceholder("Description");
        this.submitBtn.setText("Submit");


        /**
         * Event Register
         * */

        this.submitBtn.addClickListener(e->{
            boolean isValid = TaskFormValidation.validateName(this.nameTextField.getValue(),true);
            if(!isValid)return;

            TaskCreateForm  taskCreateForm = new TaskCreateForm();
            taskCreateForm.setName(this.nameTextField.getValue())
                            .setDescription(this.descriptionTextField.getValue());

            this.taskService.create(taskCreateForm);

            UI.getCurrent().navigate("");

        });
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
