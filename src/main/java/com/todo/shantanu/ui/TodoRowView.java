package com.todo.shantanu.ui;

import com.todo.shantanu.entity.Todo;
import com.todo.shantanu.exception.ResourceNotFoundException;
import com.todo.shantanu.service.TodoService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.QueryParameters;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.todo.shantanu.entity.RoutePath.EDIT_TODO;
import static java.util.Objects.nonNull;

@Slf4j
public class TodoRowView extends HorizontalLayout {

    TodoService todoService = new TodoService();

    Todo editTodo = new Todo();
    Button edit = new Button("Edit");

    public TodoRowView(Todo todo) {
        initialBasicDesign();

        HorizontalLayout hLayout = new HorizontalLayout();

        Button delete = new Button("Delete");

        TextArea title = new TextArea();
        TextArea description = new TextArea();
        DatePicker taskDate = new DatePicker();

        setFieldCss(todo, title, description, taskDate);

        edit.setId(String.valueOf(todo.getId()));
        delete.setId(String.valueOf(todo.getId()));

        hLayout.addAndExpand(title);
        hLayout.addAndExpand(description);
        hLayout.addAndExpand(taskDate);

        hLayout.addAndExpand(edit);
        hLayout.addAndExpand(delete);

        deleteButtonActionClicked(delete);

        editButtonActionClicked();

        add(hLayout);
    }

    private void setFieldCss(Todo todo, TextArea title, TextArea description, DatePicker taskDate) {
        title.setValue(todo.getTitle());
        title.setReadOnly(true);
        description.setValue(todo.getDescription());
        description.setReadOnly(true);
        taskDate.setValue(nonNull(todo.getTaskdate()) ? todo.getTaskdate() : null);
        taskDate.setReadOnly(true);
    }

    private void editButtonActionClicked() {
        edit.addClickListener(e -> {
            String id = edit.getId().orElseThrow(ResourceNotFoundException::new);

            editTodo = todoService.findById(Long.parseLong(id));

            List<String> list = new ArrayList<>();
            Map<String, List<String>> parametersMap = new HashMap<>();
            list.add(id);
            parametersMap.put("id", list);

            QueryParameters qp = new QueryParameters(parametersMap);
            UI.getCurrent().navigate(EDIT_TODO.getRoutingPath(), qp);
        });
    }

    private void deleteButtonActionClicked(Button delete) {
        delete.addClickListener(e -> {
            String id = delete.getId().orElseThrow(ResourceNotFoundException::new);
            todoService.deleteById(Long.parseLong(id));
            UI.getCurrent().getPage().reload();
        });
    }

    private void initialBasicDesign() {
        setWidth("70%");
        setAlignItems(Alignment.CENTER);
    }
}
