package com.kids.crm.todo.service;

import com.kids.crm.todo.model.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;

@RunWith(JUnit4.class)
public class TodoServiceTest {


    TodoService todoService = new TodoServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void blankTitle_shouldFailCreate(){
        Todo todo = new Todo();
        todo.setDescription("ABC");
        todo.setCompletionDate(LocalDate.now().plusDays(2));
        todoService.create(todo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dateBeforeToday_shouldFailOnUpdate(){
        Todo todo = new Todo();
        todo.setDescription("ABC");
        todo.setCompletionDate(LocalDate.now().minusDays(2));
        todoService.update(todo);
    }


}
