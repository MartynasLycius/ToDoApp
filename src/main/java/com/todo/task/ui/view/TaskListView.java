package com.todo.task.ui.view;

import com.todo.task.entity.Task;
import com.todo.task.service.TaskService;
import com.todo.user.entity.User;
import com.todo.user.service.UserSerive;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class TaskListView extends Div {
    private static final Logger logger= LogManager.getLogger(TaskListView.class);
    private final TaskService taskService;
    private final UserSerive userSerive;
    private final TaskView taskView;
    private final User loggedUser;

    private LocalDate taskDate;
    private final ListBox<Task> listBox = new ListBox<>();
    private List<Task> taskList;
    //private final Button showDetailsBtn;
    private final Button addBtn;
    private final Button selectedTaskDateElem;

    public TaskListView(TaskService taskService,
                        UserSerive userSerive,
                        TaskView taskView,
                        LocalDate taskDate,
                        User loggedUser){
        this.taskService=taskService;
        this.userSerive=userSerive;
        this.taskDate=taskDate;
        this.taskView=taskView;
        this.loggedUser=loggedUser;
        taskView.setListBox(this.listBox);
        logger.debug("taskDate: "+this.taskDate);

        Icon addTaskIcon = new Icon(VaadinIcon.PLUS_CIRCLE);
        addBtn = new Button("New Task",addTaskIcon);
        addBtn.addClassName("add-new-task-btn");
        addBtn.addThemeVariants(ButtonVariant.LUMO_SMALL);
        addBtn.addClickListener(e->showTaskForm(new Task()));

        /**todo*/
        /*Icon viewTaskIcon= new Icon(VaadinIcon.VIEWPORT);
        showDetailsBtn = new Button(viewTaskIcon);
        showDetailsBtn.addThemeVariants(ButtonVariant.LUMO_SMALL);
        showDetailsBtn.addClickListener(e->showTaskDetails(demoTask()));*/

        selectedTaskDateElem= new Button(LocalDate.now().toString());
        selectedTaskDateElem.addClassName("selected-task-date");
        selectedTaskDateElem.getStyle().set("float","left");

        Div listBoxContainer= new Div(listBox);
        listBoxContainer.addClassName("list-box-container");

        configListBox();
        updateList(taskDate);
        showTaskForm(new Task());

        addClassName("tasklist-view");
        add(listBoxContainer, selectedTaskDateElem, addBtn);
    }

    public void updateList(LocalDate taskDate){
        this.taskDate=taskDate;
        selectedTaskDateElem.setText(taskDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        taskView.updateList(taskDate, listBox);
    }

    private void configListBox(){
        listBox.setRenderer(new ComponentRenderer<>(item -> {
            Label label = new Label();
            label.add(getListItem(item));
            return label;
        }));
        listBox.addValueChangeListener(e->showTaskForm(e.getValue()));
    }

    private void showTaskDetails(Task task){
        taskView.showTaskDetails(task);
    }

    private void showTaskForm(Task task){
        taskView.showTaskForm(task);
    }

    private static Component getListItem(Task task){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        Div listItem = new Div();
        listItem.addClassName("task-list-item");
        Span time= new Span(task.getTime().format(dateTimeFormatter));
        time.addClassName("task-list-item-time");
        Span taskName= new Span(task.getTaskName());
        taskName.addClassName("task-list-item-name");
        listItem.add(time, taskName);
        return listItem;
    }
}
