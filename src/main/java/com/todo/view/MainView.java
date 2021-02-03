package com.todo.view;

import com.todo.controller.HistoryEditor;
import com.todo.controller.HistoryRepository;
import com.todo.controller.TaskEditor;
import com.todo.controller.TaskRepository;
import com.todo.model.History;
import com.todo.model.Task;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {

    public  final Button taskButton,historyButton;
    public TaskRepository task_repo;
    public TaskEditor task_editor;
    public HistoryRepository hist_repo;
    public HistoryEditor hist_editor;

    // MainView of Vaadin
    public MainView(TaskRepository task_repo, TaskEditor task_editor, HistoryRepository hist_repo, HistoryEditor hist_editor) {

        this.task_repo=task_repo;
        this.task_editor=task_editor;
        this.hist_repo=hist_repo;
        this.hist_editor=hist_editor;
        /// creating todolist & history button
        taskButton = new Button("Your ToDo List", VaadinIcon.ADJUST.create());
        historyButton = new Button("History", VaadinIcon.ADJUST.create());

        HorizontalLayout actions = new HorizontalLayout (taskButton,historyButton);
        /// adding buttons to layout
        add(actions);
        TaskPage viewPage=new TaskPage(this);

        taskButton.addClickListener(e ->{
            removeAll();
            add(actions);
            new TaskPage(this); // showing task list
        });

        historyButton.addClickListener(e ->{
            removeAll();
            add(actions);
            new HistoryPage(this); // showing history list
        });



    }

}
