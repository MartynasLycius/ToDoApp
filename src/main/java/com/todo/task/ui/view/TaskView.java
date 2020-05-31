package com.todo.task.ui.view;

import com.todo.task.entity.Comment;
import com.todo.task.entity.Task;
import com.todo.task.service.CommentService;
import com.todo.task.service.TaskService;
import com.todo.user.entity.User;
import com.todo.user.service.UserSerive;
import com.todo.utils.Utils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TaskView extends Div {
    private static final Logger logger= LogManager.getLogger(TaskView.class);

    private final TaskService taskService;
    private final CommentService commentService;
    private final UserSerive userSerive;
    private final User loggedUser;

    private LocalDate taskDate;
    private TaskForm form;
    private Component viewOfTask;
    private ListBox<Task> listBox;

    public TaskView(TaskService taskService,
                    CommentService commentService,
                    UserSerive userSerive,
                    LocalDate taskDate,
                    User loggedUser) {
        this.taskService=taskService;
        this.commentService=commentService;
        this.userSerive=userSerive;
        this.taskDate=taskDate;
        this.loggedUser=loggedUser;

        this.form = new TaskForm();
        form.addListener(TaskForm.SaveEvent.class, this::saveTask);
        form.addListener(TaskForm.DeleteEvent.class, this::deleteTask);
        form.addListener(TaskForm.CancelEvent.class, e -> cancelTaskAdding());

        addClassName("task-view");
        add(form);
    }

    void setListBox(ListBox<Task> listBox){
        this.listBox=listBox;
    }

    void showTaskDetails(Task task){
        if (this.viewOfTask!=null){
            this.remove(this.viewOfTask);
        }
        hideTaskForm();
        this.viewOfTask=generateTaskDetailsView(task);
        this.add(viewOfTask);
    }

    void hideTaskDetails(){
        if (this.viewOfTask!=null){
            this.remove(this.viewOfTask);
        }
        this.viewOfTask=null;
    }

    void showTaskForm(Task task){
        hideTaskDetails();
        this.form.setTask(task);
        this.form.setVisible(true);
    }
    void hideTaskForm(){
        form.setTask(new Task());
        this.form.setVisible(false);
    }

    private void deleteTask(TaskForm.DeleteEvent evt) {
        if (evt.getTask().isPersisted()){
            taskService.delete(evt.getTask());
            Notification notification = new Notification(
                    "Task deleted successfully", 3000,
                    Notification.Position.TOP_CENTER);
            notification.open();
            updateList(taskDate, listBox);
            showTaskForm(new Task());
        }
    }

    private void saveTask(TaskForm.SaveEvent evt) {
        logger.debug("saving task");
        Task taskObj= evt.getTask();
        taskObj.setTaskOwner(loggedUser);
        taskService.save(taskObj);
        Notification notification = new Notification(
                "Task saved successfully", 3000,
                Notification.Position.TOP_CENTER);
        notification.open();

        logger.debug("selected-taskDate:: "+taskDate);
        logger.debug("form-taskDate:: "+taskObj.getDate());
        if (taskObj.getDate().equals(taskDate)){
            updateList(taskDate, listBox);
        }
        showTaskDetails(taskObj);
    }

    private void cancelTaskAdding() {
        logger.debug("task form cancelling.");
        showTaskForm(new Task());
    }

    public void updateList(LocalDate taskDate, ListBox<Task> listBox){
        this.taskDate=taskDate;
        List<Task> taskList= taskService.findAllByUserAndDate(loggedUser, taskDate);
        for (Task task: taskList){
            task.setComments(new ArrayList<>());
        }
        listBox.setItems(taskList);
    }

    private static Component generateTaskDetailsView(Task task){

        Div viewBox = new Div();
        viewBox.addClassName("view-box");

        Paragraph taskTime= new Paragraph();
        Span timeTitle= new Span("Time: ");
        timeTitle.setClassName("task-view-heading");
        taskTime.add(timeTitle, new Text(task.getTime().toString()));

        Paragraph taskName= new Paragraph();
        Span taskNameTitle= new Span("Task Name: ");
        taskNameTitle.setClassName("task-view-heading");
        taskName.add(taskNameTitle, new Text(task.getTaskName()));

        Paragraph taskDescription= new Paragraph();
        Span descTitle= new Span("Description: ");
        descTitle.setClassName("task-view-heading");
        taskDescription.add(descTitle, new Text(task.getDescription()));

        Paragraph taskComments= new Paragraph();
        if (!Utils.isEmpty(task.getComments())){
            Span commentTitle= new Span("Comments: ");
            commentTitle.setClassName("task-view-heading");
            Div commentListContainer= new Div();
            commentListContainer.setClassName("comment-box-container");
            taskComments.add(commentTitle, commentListContainer);

            UnorderedList commentList= new UnorderedList();
            ListItem listItem = null;
            for(Comment comment: task.getComments()){
                listItem= new ListItem(comment.getComment());
                listItem.setClassName("comment-view");
                commentList.add(listItem);
            }
            commentList.setClassName("comment-view-list");
            commentListContainer.add(commentList);
        }

        viewBox.add(taskTime, taskName, taskDescription, taskComments);

        return viewBox;
    }
}
