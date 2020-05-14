package com.todo.shantanu.ui;

import com.todo.shantanu.entity.Todo;
import com.todo.shantanu.service.TodoService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.time.LocalDate;

import static com.todo.shantanu.entity.RoutePath.ROOT;
import static com.vaadin.flow.data.value.ValueChangeMode.EAGER;

@Route("todoView")
@PageTitle("Todo Form")
@Theme(value = Lumo.class)
public class TodoView extends VerticalLayout {

    TodoService todoService = new TodoService();

    public TodoView() {

        initialBasicDesign();

        Binder<Todo> binder = new Binder<>();

        TextField title = new TextField();
        TextArea description = new TextArea();
        DatePicker taskDate = new DatePicker();

        Button save = new Button("Save");
        Button reset = new Button("Reset");
        Button back = new Button("Back");

        setFieldCss(title, description, taskDate);
        setBtnCss(save);

        fieldBinding(binder, title, description, taskDate);

        saveClickListener(binder, todoService, title, description, taskDate, save);
        reset.addClickListener(event -> binder.readBean(null));
        back.addClickListener(e -> UI.getCurrent().navigate(ROOT.getRoutingPath()));

        HorizontalLayout buttons = new HorizontalLayout(back, save, reset);

        VerticalLayout v = new VerticalLayout();
        v.setAlignItems(Alignment.CENTER);
        v.add(title, description, taskDate);
        v.add(buttons);
        add(v);
    }

    private void fieldBinding(Binder<Todo> binder, TextField title, TextArea description, DatePicker taskDate) {

        titleValidator(binder, title);

        binder.bind(taskDate, Todo::getTaskdate, Todo::setTaskdate);
        binder.bind(description, Todo::getDescription, Todo::setDescription);
    }

    private void setFieldCss(TextField title, TextArea description, DatePicker taskdate) {
        title.setValueChangeMode(EAGER);
        title.setPlaceholder("Title");
        title.setRequired(true);
        title.setLabel("Title");
        description.setValueChangeMode(EAGER);
        description.setPlaceholder("Description");
        description.setLabel("Description");
        taskdate.setPlaceholder("Date");
        taskdate.setClearButtonVisible(true);
        taskdate.setMin(LocalDate.now());
        taskdate.setLabel("Date");
    }

    private void setBtnCss(Button save) {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }

    private void titleValidator(Binder<Todo> binder, TextField title) {
        SerializablePredicate<String> titlePredicate = value -> !title.getValue().trim().isEmpty();

        Binder.Binding<Todo, String> titleBinding = binder.forField(title)
                .withValidator(titlePredicate,
                        "Title cannot be empty")
                .bind(Todo::getTitle, Todo::setTitle);

        title.addValueChangeListener(event -> titleBinding.validate());
        title.setRequiredIndicatorVisible(true);
    }

    private void saveClickListener(Binder<Todo> binder, TodoService todoService,
                                   TextField title, TextArea desc, DatePicker date, Button save) {
        save.addClickListener(e -> {
            Todo todo = setData(title, desc, date);
            if (binder.writeBeanIfValid(todo)) {
                todoService.save(todo);
                Notification.show("Save successfully!");

                UI.getCurrent().navigate(ROOT.getRoutingPath());
            }
        });
    }

    private Todo setData(TextField title, TextArea description, DatePicker taskDate) {
        Todo todo = new Todo();
        todo.setDescription(description.getValue());
        todo.setTaskdate(taskDate.getValue());
        todo.setTitle(title.getValue());
        return todo;
    }

    private void initialBasicDesign() {
        setWidth("80%");
        setHeight("100%");
        setAlignItems(Alignment.CENTER);
    }
}
