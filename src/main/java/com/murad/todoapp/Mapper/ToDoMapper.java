package com.murad.todoapp.Mapper;

import com.murad.todoapp.domain.ToDo;
import com.murad.todoapp.utility.Maneger;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class ToDoMapper {

    public void mapTodoForAdd(ToDo todo) {
        mapToDoForEdit(todo, todo);
    }

    public void mapToDoForEdit(ToDo todo, ToDo todoForEdit) {
        todoForEdit.setItemDescription(todo.getItemDescription());
        try {
            todoForEdit.setStartDate(LocalDate.parse(todo.getStartDateString(), Maneger.formatter).plusDays(1));
        } catch (DateTimeParseException n) {
            todoForEdit.setStartDate(null);
        }
        try {
            todoForEdit.setEndDate(LocalDate.parse(todo.getEndDateString(), Maneger.formatter).plusDays(1));
        } catch (DateTimeParseException n) {
            todoForEdit.setEndDate(null);
        }
    }



}
