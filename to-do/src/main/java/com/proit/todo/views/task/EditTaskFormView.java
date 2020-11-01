package com.proit.todo.views.task;

import com.proit.todo.data.entity.Task;
import com.proit.todo.data.service.TaskService;
import com.proit.todo.views.NotFound.NotFoundView;
import com.proit.todo.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;

import java.util.Optional;

@Route(value = "edit-task", layout = MainView.class)
@PageTitle("Task Form")
@CssImport("./styles/views/task/task-form-view.css")
public class EditTaskFormView extends Div implements HasUrlParameter<Integer>, BeforeEnterObserver {

    private TextField name = new TextField("Name");
    private TextArea description = new TextArea("Description");
    private DatePicker taskDate = new DatePicker("Task Date");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<Task> binder = new Binder(Task.class);
    private Task task;

    private TaskService taskService;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer id) {
        setTaskData(id);
    }

    private void setTaskData(Integer id){

        Optional<Task> taskFromBackend = this.taskService.get(id);
        if (taskFromBackend.isPresent()) {
            this.task = taskFromBackend.get();
            this.name.setValue(task.getName());
            this.description.setValue(task.getDescription());
            this.taskDate.setValue(task.getTaskDate());
        } else {
            Notification.show("Task not found");
        }

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (this.task==null) {
            event.forwardTo(NotFoundView.class);
        }
    }

    public EditTaskFormView(TaskService taskService) {
        setId("task-form-view");
        this.taskService = taskService;

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());


        //manual binding for name field with validation
        binder.forField(name).asRequired("Task name is required").withValidator(
                name -> name.length() >= 5,
                "Task Name must contain at least five characters").bind(Task::getName,Task::setName);

        //auto binding for others field
        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> UI.getCurrent()
                .navigate("task-list"));
        save.addClickListener(e -> {
            try {
                binder.validate();
                binder.writeBean(this.task);
                taskService.update(this.task);
                Notification.show("Task details stored.");
                UI.getCurrent().navigate("task-list");
            }
            catch (Exception ex){
                Notification.show("An exception happened while trying to store the task details.");
            }

        });
    }


    private Component createTitle() {
        return new H3("Task information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(name,description,taskDate);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}
