package com.santam.todolycias.ui;

import com.santam.todolycias.backend.entity.Todo;
import com.santam.todolycias.backend.service.TodoService;
import com.santam.todolycias.backend.util.TaskStatus;
import com.santam.todolycias.backend.util.UtilTask;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Route(value = "", layout = TodoAppLayout.class)
@PWA(name = "ToDo App (ProIT)",
        shortName = "ToDo App",
        description = "Interview Task.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PageTitle("ToDo | ProIT")
public class MainView extends VerticalLayout {

    Grid<Todo> dataGrid = new Grid<>(Todo.class);
    TodoService todoService;
    UI vaadinUI;

    public MainView(@Autowired TodoService todoService, @Autowired UI vaadinUI) {

        this.todoService = todoService;
        this.vaadinUI = vaadinUI;

        addClassName("todolist");
        setSizeFull();
        setWidth("70%");
        getStyle().set("margin-left", "15%");

        Button addTodoItem = new Button("+ Add Task");
        addTodoItem.getStyle()
                .set("font-weight", "bold")
                .set("border-radius", "30px")
                .set("margin-bottom", "10px")
                .set("background-color", "#3FB246")
                .set("color", "#FFFFFF");
        addTodoItem.addClickListener(event -> {
            vaadinUI.getPage().setLocation("/add");
        });

        add(addTodoItem);
        populateTodoItem();
    }

    private void populateTodoItem() {
        List<Todo> todoList = todoService.findAll();

        if (todoList.isEmpty()) {
            add(new Paragraph("There is no item to do. Please add new task in the list."));
        } else {
            todoList.forEach(todo -> {
                String taskStatus = TaskStatus.valueOf("S" + todo.getStatuss()).getAction();
                HorizontalLayout todoItemDetails = new HorizontalLayout();
                Div statusIndicator = new Div();
                statusIndicator.setHeightFull();
                statusIndicator.setWidth("8px");
                statusIndicator.getStyle().set("background-color", todoDivIndicatorColorCode(todo.getStatuss()));

                Checkbox checkbox = new Checkbox();
                checkbox.getStyle().set("margin-left", "5px");
                checkbox.setValue((taskStatus.equalsIgnoreCase("done") ? true : false ));
                checkbox.addValueChangeListener(valueChange -> {
                    String message = "Task Done.";
                    if(valueChange.getValue()){
                        todo.setStatuss(4);
                    }
                    else{
                        message = "Task Reeset.";
                        todo.setStatuss(0);
                    }

                    todo.setUpdatedAt(new Timestamp(new Date().getTime()));
                    todoService.saveTodo(todo);
                    UtilTask.showNotificaiton(false, message);
                    vaadinUI.getCurrent().getPage().reload();
                });

                VerticalLayout taskTitleLayout = new VerticalLayout();
                taskTitleLayout.getElement().removeAttribute("theme");
                taskTitleLayout.setJustifyContentMode(JustifyContentMode.START);
                taskTitleLayout.getStyle().set("margin-left", "2%");

                Span taskTitle = new Span(todo.getTitle());
                taskTitle.setWidthFull();
                taskTitle.getStyle()
                        .set("font-size", "16px")
                        .set("font-weight", "bold");
                Span dueDate = new Span();
                if(!taskStatus.equalsIgnoreCase("done")) {
                    dueDate.setText(readableDeadline(todo.getDeadline()));
                    dueDate.setWidthFull();
                    dueDate.setWidth("140px");
                    dueDate.setMaxWidth("200px");
                    dueDate.setMaxHeight("20px");
                    dueDate.getStyle()
                            .set("font-size", "12px")
                            .set("font-weight", "bold")
                            .set("padding", "3px")
                            .set("margin-left", "5px")
                            .set("border-radius", "30px")
                            .set("text-align", "center")
                            .set("color", "#ffffff")
                            .set("background-color", getDeadlineBackground(dueDate.getText()));
                }
                Span currentStatus = new Span(taskStatus);
                currentStatus.setWidthFull();
                currentStatus.setWidth("140px");
                currentStatus.setMaxWidth("200px");
                currentStatus.setMaxHeight("20px");
                currentStatus.getStyle()
                        .set("font-size", "12px")
                        .set("font-weight", "bold")
                        .set("padding", "3px")
                        .set("margin-left", "18px")
                        .set("border-radius", "30px")
                        .set("text-align", "center")
                        .set("color", "#ffffff")
                        .set("background-color", todoDivIndicatorColorCode(todo.getStatuss()));

                HorizontalLayout dateTileLayout = new HorizontalLayout();
                dateTileLayout.add(taskTitle, dueDate, currentStatus);

                Span taskOwner = new Span("Responsible: " + todo.getUsersByOwnby().getName());
                taskOwner.getStyle().set("font-size", "12px");
                Span detailsTodo = new Span("Details");
                detailsTodo.getStyle()
                        .set("font-size", "14px")
                        .set("padding", "2px 10px")
                        .set("cursor", "pointer")
                        .set("background-color", "#1d1d1d")
                        .set("color", "#ffffff");
                detailsTodo.addClickListener(event -> showTodoDetails(todo));

                taskTitleLayout.add(dateTileLayout, taskOwner, detailsTodo);


                HorizontalLayout buttonLayout = new HorizontalLayout();
                Span editTodo = new Span("Edit");
                editTodo.getStyle()
                        .set("font-size", "14px")
                        .set("padding", "2px 10px")
                        .set("border-radius", "30px")
                        .set("cursor", "pointer")
                        .set("background-color", "#3FA52B")
                        .set("color", "#ffffff");
                editTodo.addClickListener(event -> vaadinUI.getPage().setLocation("/edit/" + todo.getId()));
                Span deleteTodo = new Span("Delete");
                deleteTodo.getStyle()
                        .set("font-size", "14px")
                        .set("padding", "2px 10px")
                        .set("border-radius", "30px")
                        .set("cursor", "pointer")
                        .set("background-color", "#CD3E3E")
                        .set("color", "#ffffff");
                deleteTodo.addClickListener(event -> deleteDialog(todo));
                buttonLayout.setJustifyContentMode(JustifyContentMode.END);
                buttonLayout.setAlignItems(Alignment.END);
                buttonLayout.getStyle().set("margin", "0px 5px 0px 40px");

                buttonLayout.add(editTodo, deleteTodo);

                todoItemDetails.getElement().removeAttribute("theme");
                todoItemDetails.getStyle()
                        .set("margin", "5px")
                        .set("box-shadow", "0px 1px 1px 1px #DCDCDC")
                        .set("padding", "2px");
                todoItemDetails.setSizeFull();
                todoItemDetails.setWidth("100%");
                todoItemDetails.setMinHeight("40px");
                todoItemDetails.setHeight("auto");
                todoItemDetails.setMaxHeight("120px");
                todoItemDetails.setDefaultVerticalComponentAlignment(Alignment.CENTER);
                todoItemDetails.add(statusIndicator, checkbox, taskTitleLayout, buttonLayout);


                add(todoItemDetails);

            });


        }
    }


    private void deleteDialog(Todo todo) {
        Dialog dialog = new Dialog();

        H4 warningText = new H4("Are you sure to delete this Task <" + todo.getTitle() + "> ?");
        Span actionLabel = new Span("");

        Button yesButton = new Button("Delete It!", event -> {
            try {

                todoService.deleteTodo(todo);
                actionLabel.setText("This task <" + todo.getTitle() + "> deleted.");
                vaadinUI.getCurrent().getPage().reload();
            } catch (Exception e) {
                actionLabel.setText("Couldn't deleted. Please try again");
            }
        });
        yesButton.getStyle()
                .set("font-weight", "bold")
                .set("border-radius", "30px")
                .set("margin-bottom", "10px")
                .set("background-color", "#CD3E3E")
                .set("color", "#FFFFFF");
        Button noButton = new Button("Nope", event -> dialog.close());
        noButton.getStyle()
                .set("font-weight", "bold")
                .set("border-radius", "30px")
                .set("margin-bottom", "10px")
                .set("background-color", "#3FB246")
                .set("color", "#FFFFFF");

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setJustifyContentMode(JustifyContentMode.END);
        buttonLayout.setAlignItems(Alignment.END);
        buttonLayout.add(yesButton, noButton);

        dialog.add(new VerticalLayout(warningText, buttonLayout, actionLabel));
        dialog.open();
    }


    private void showTodoDetails(Todo todo) {
        Dialog dialog = new Dialog();

        VerticalLayout detailsLayout = new VerticalLayout();

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setMaxHeight("30px");
        topLayout.setHeight("30px");
        topLayout.setWidthFull();
        Div indicatorDiv = new Div();
        indicatorDiv.setWidth("10px");
        indicatorDiv.getStyle().set("background-color", "#000000");
        indicatorDiv.setHeightFull();
        Span titleText = new Span("Task Details");
        titleText.getStyle().set("margin-left", "10px").set("font-size", "20px").set("font-weight", "bold");
        titleText.setWidth("150px");
        titleText.setSizeFull();
        topLayout.add(indicatorDiv, titleText);


        HorizontalLayout taskTitleLayout = new HorizontalLayout();
        taskTitleLayout.setWidthFull();
        Span lblTitle = new Span("Task Title");
        lblTitle.getStyle().set("font-size","14px").set("font-weight","bold");
        lblTitle.setWidth("20%");
        Span taskTitleText = new Span(todo.getTitle());
        taskTitleText.getStyle().set("font-size","14px");
        taskTitleText.setWidth("80%");
        taskTitleLayout.add(lblTitle, taskTitleText);

        HorizontalLayout taskDetailsLayout = new HorizontalLayout();
        taskDetailsLayout.setWidthFull();
        Span lblDetals = new Span("Task Details");
        lblDetals.getStyle().set("font-size","14px").set("font-weight","bold");
        lblDetals.setWidth("20%");
        Span taskDetailsText = new Span(todo.getDescc());
        taskDetailsText.getStyle().set("font-size","14px");
        taskDetailsText.setWidth("80%");
        taskDetailsLayout.add(lblDetals, taskDetailsText);

        HorizontalLayout taskStatusLayout = new HorizontalLayout();
        taskStatusLayout.setWidthFull();
        Span lblStatus = new Span("Task Status");
        lblStatus.getStyle().set("font-size","14px").set("font-weight","bold");
        lblStatus.setWidth("20%");
        Span taskStatusText = new Span(TaskStatus.valueOf("S" + todo.getStatuss()).getAction());
        taskStatusText.getStyle().set("font-size","14px");
        taskStatusText.setWidth("80%");
        taskStatusLayout.add(lblStatus, taskStatusText);

        HorizontalLayout taskResponsibleLayout = new HorizontalLayout();
        taskResponsibleLayout.setWidthFull();
        Span lblResponsible = new Span("Responsible");
        lblResponsible.getStyle().set("font-size","14px").set("font-weight","bold");
        lblResponsible.setWidth("20%");
        Span taskResponsibleText = new Span(todo.getUsersByOwnby().getName());
        taskResponsibleText.getStyle().set("font-size","14px");
        taskResponsibleText.setWidth("80%");
        taskResponsibleLayout.add(lblResponsible, taskResponsibleText);


        HorizontalLayout taskImportantDateLayout = new HorizontalLayout();
        topLayout.setWidthFull();
        Span lblImportantDates = new Span("Important Date");
        lblImportantDates.getStyle().set("font-size","14px").set("font-weight","bold");
        lblImportantDates.setWidth("20%");
        Span taskCreatedatText = new Span("Created At: "+todo.getCreatedAt());
        taskCreatedatText.getStyle().set("font-size","14px");
        Span taskUpdatedatText = new Span("Updated At: "+todo.getUpdatedAt());
        taskUpdatedatText.getStyle().set("font-size","14px");
        Span taskDeadlineText = new Span("Deadline : "+todo.getDeadline());
        taskDeadlineText.getStyle().set("font-size","14px");
        VerticalLayout datesLayout = new VerticalLayout();
        datesLayout.setWidth("80%");
        datesLayout.add(taskCreatedatText, taskUpdatedatText, taskDeadlineText);
        taskImportantDateLayout.add(lblImportantDates,datesLayout);

        dialog.add(new VerticalLayout(topLayout, taskTitleLayout, taskDetailsLayout, taskStatusLayout, taskResponsibleLayout, taskImportantDateLayout));
        dialog.open();
    }

    private void populateGrid() {
        dataGrid.addClassName("todo-list");
        dataGrid.setSizeFull();
        dataGrid.removeColumnByKey("status");
        dataGrid.removeColumnByKey("updatedAt");
        dataGrid.setColumns("title", "desc", "ownby", "status", "deadline");
        dataGrid.addColumn(todo -> todo.getUpdatedAt());

        add(dataGrid);

        dataGrid.setItems(todoService.findAll());
    }


    private String getDeadlineBackground(String deadline) {
        if (deadline.toLowerCase().equalsIgnoreCase("due")) {
            return "#CD3E3E";
        } else {
            return "#3FA52B";
        }
    }

    private String todoDivIndicatorColorCode(long status) {
        String colorCode = "#000000";
        switch ((int) status) {
            case 1:
                colorCode = "#8422C8";
                break;
            case 2:
                colorCode = "#E17627";
                break;
            case 3:
                colorCode = "#E54343";
                break;
            case 4:
                colorCode = "#3FB246";
                break;

        }
        return colorCode;
    }

    private String readableDeadline(Timestamp deadline) {

        Date fromDate = new Date();
        Date toDate = new Date(deadline.getTime());

        long diff = toDate.getTime() - fromDate.getTime();
        String dateFormat = "";
        if (fromDate.after(toDate) || fromDate.equals(toDate)) {
            dateFormat = "Due";
        } else {
            int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
            if (diffDays > 0) {
                dateFormat += diffDays + "d ";
            }
            diff -= diffDays * (24 * 60 * 60 * 1000);

            int diffhours = (int) (diff / (60 * 60 * 1000));
            if (diffhours > 0) {
                dateFormat += diffhours + "h ";
            }
            diff -= diffhours * (60 * 60 * 1000);

            int diffmin = (int) (diff / (60 * 1000));
            if (diffmin > 0) {
                dateFormat += diffmin + "m ";
            }
        }

//        diff -= diffmin * (60 * 1000);

//        int diffsec = (int) (diff / (1000));
//        if(diffsec>0){
//            dateFormat+=diffsec+" sec";
//        }
        System.out.println(dateFormat);
        return dateFormat;
    }
}
