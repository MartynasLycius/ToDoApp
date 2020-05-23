package com.santam.todolycias.ui;

import com.santam.todolycias.backend.entity.Todo;
import com.santam.todolycias.backend.entity.Users;
import com.santam.todolycias.backend.service.TodoService;
import com.santam.todolycias.backend.service.UserService;
import com.santam.todolycias.backend.util.UtilTask;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

@Route(value = "add", layout = TodoAppLayout.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PageTitle("Add ToDo | ProIT")
public class TodoAddView extends VerticalLayout {

    TodoService todoService;
    UserService userService;
    UI vaadinUI;

    TextField taskTitle = new TextField("Task Title");
    TextArea taskDetails = new TextArea("Task Details");
    DatePicker deadlineDate = new DatePicker("Pick Finishing date");
    TimePicker deadlineTime = new TimePicker("Set Finishing time");
    ComboBox<Users> userComboBox = new ComboBox<>();
    ComboBox<String> statusComboBox = new ComboBox<>();
    Users loggedInUser;

    public TodoAddView(@Autowired TodoService todoService, @Autowired UserService userService, @Autowired UI vaadinUI) {

        this.todoService = todoService;
        this.userService = userService;
        this.vaadinUI = vaadinUI;

        String userEmail = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }
        loggedInUser = userService.findByEmail(userEmail).get();

        addClassName("add-todolist");
        setSizeFull();
        setWidth("70%");
        getStyle().set("margin-left", "15%");

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setMaxHeight("30px");
        topLayout.setHeight("30px");
        topLayout.setWidthFull();
        Div indicatorDiv = new Div();
        indicatorDiv.setWidth("10px");
        indicatorDiv.getStyle().set("background-color", "#E2D645");
        indicatorDiv.setHeightFull();
        Span titleText = new Span("Add Task");
        titleText.getStyle().set("margin-left", "10px").set("font-size", "20px").set("font-weight", "bold");
        titleText.setWidth("150px");
        titleText.setSizeFull();

        HorizontalLayout backtoListLay = new HorizontalLayout();
        backtoListLay.setDefaultVerticalComponentAlignment(Alignment.END);
        backtoListLay.setJustifyContentMode(JustifyContentMode.END);
        backtoListLay.setAlignItems(Alignment.END);
        backtoListLay.setSizeFull();
        backtoListLay.add(new RouterLink("All Todo", MainView.class));
        topLayout.add(indicatorDiv, titleText, backtoListLay);
        add(topLayout);

        taskTitle.setWidthFull();
        taskTitle.setRequired(true);
        taskTitle.setRequiredIndicatorVisible(true);

        taskDetails.setWidthFull();
        deadlineDate.setMin(LocalDate.now());
        deadlineDate.setRequired(true);
        deadlineDate.setRequiredIndicatorVisible(true);
        deadlineTime.setRequired(true);


        deadlineDate.setValue(LocalDate.now().plusDays(1));
        deadlineTime.setValue(LocalTime.now());
        HorizontalLayout dateTimeLayput = new HorizontalLayout();
        dateTimeLayput.add(deadlineDate, deadlineTime);


        statusComboBox.setItems(UtilTask.statusList);
        statusComboBox.setLabel("Select Task Status");
        statusComboBox.setValue(UtilTask.statusList[0]);
        statusComboBox.setRequired(true);
        statusComboBox.setRequiredIndicatorVisible(true);


        userComboBox.setItems(userService.findAll());
        userComboBox.setItemLabelGenerator(users -> users.getName());
        userComboBox.setLabel("Select Responsible Person");
        userComboBox.setValue(loggedInUser);
        userComboBox.setRequired(true);
        userComboBox.setRequiredIndicatorVisible(true);

        HorizontalLayout comboBoxLayput = new HorizontalLayout();
        comboBoxLayput.add(statusComboBox, userComboBox);


        Button addTodoItem = new Button("Save Task");
        addTodoItem.getStyle()
                .set("font-weight", "bold")
                .set("border-radius", "30px")
                .set("margin-bottom", "10px")
                .set("background-color", "#3FB246")
                .set("color", "#FFFFFF");
        addTodoItem.addClickListener(event -> {

            String title = taskTitle.getValue().trim();
            String desc = taskDetails.getValue().trim();
            String status = statusComboBox.getValue().trim();
            Users responsible = userComboBox.getValue();

            if (taskTitle.isEmpty()) {
                taskTitle.setErrorMessage("Please provide Task Title");
                taskTitle.setInvalid(true);
            } else {
                if (status.isEmpty()) {
                    statusComboBox.setErrorMessage("Please select a Status");
                    statusComboBox.setInvalid(true);
                } else {
                    if (responsible == null) {
                        userComboBox.setErrorMessage("Please select a Responsible Person");
                        userComboBox.setInvalid(true);
                    } else {
                        if (deadlineDate.isInvalid()) {
                            deadlineDate.setErrorMessage("Please select valid Date");
                            deadlineDate.setInvalid(true);
                        } else {
                            if (deadlineTime.isInvalid()) {
                                deadlineTime.setErrorMessage("Please select valid time");
                                deadlineTime.setInvalid(true);
                            } else {
                                LocalDateTime deadlineDateTime = deadlineDate.getValue().atTime(deadlineTime.getValue());
                                Timestamp deadlineTimestamp = Timestamp.valueOf(deadlineDateTime);
                                System.out.println(deadlineTimestamp);
                                Todo todo = new Todo();
                                todo.setTitle(title);
                                todo.setDescc(desc);
                                todo.setStatuss(Arrays.asList(UtilTask.statusList).indexOf(status));
                                todo.setUsersByOwnby(responsible);
                                todo.setCreatedAt(new Timestamp(new Date().getTime()));
                                todo.setUpdatedAt(new Timestamp(new Date().getTime()));
                                todo.setDeadline(deadlineTimestamp);
                                try {
                                    todoService.saveTodo(todo);
                                    UtilTask.showNotificaiton(false, "Task saved. To see all task go back to the home page");
                                    clearForm();
                                } catch (Exception e) {
                                    UtilTask.showNotificaiton(true, "There is an error occurred. Please try again. Err: " + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }


        });
        Button backToList = new Button("Back");
        backToList.getStyle()
                .set("font-weight", "bold")
                .set("border-radius", "30px")
                .set("margin-bottom", "10px")
                .set("background-color", "#dddddd")
                .set("color", "#000000");
        backToList.addClickListener(event -> {
            vaadinUI.getPage().setLocation("/");
        });
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(addTodoItem, backToList);

        add(taskTitle, taskDetails, dateTimeLayput, comboBoxLayput, buttonLayout);

    }

    private void clearForm(){
        taskTitle.setValue("");
        taskDetails.setValue("");
        deadlineDate.setValue(LocalDate.now().plusDays(1));
        deadlineTime.setValue(LocalTime.now());
        statusComboBox.setValue(UtilTask.statusList[0]);
        userComboBox.setValue(loggedInUser);
    }

}
