package com.vaadin.todo.test;

import com.vaadin.todo.entity.TodoItem;
import com.vaadin.todo.service.ToDoItemService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoItemServiceUnitTest {

    @Mock
    private ToDoItemService toDoItemService;

    List<TodoItem> todoItemList = new ArrayList<>();
    @Test
    public void saveTodoItemAndFindByIdCheck() throws Exception {
        TodoItem toDoItem = new TodoItem();
        toDoItem.setItemName("Sql Injection");
        toDoItem.setDescription("Have to prevent Sql injection full application");
        toDoItem.setCreatedDate(new Date());
        TodoItem insertedTodoItem = toDoItemService.save(toDoItem);
        Assert.assertNotNull(insertedTodoItem);
        Assert.assertEquals(insertedTodoItem, toDoItem);
        Assert.assertNotNull(toDoItemService.findById(toDoItem.getId()));

        TodoItem toDoItem1 = new TodoItem();
        toDoItem1.setItemName("Vendor List");
        toDoItem1.setDescription("Create vendor list view page");
        toDoItem1.setCreatedDate(new Date());
        TodoItem insertedTodoItem1 = toDoItemService.save(toDoItem);
        Assert.assertNotNull(insertedTodoItem1);
        Assert.assertEquals(insertedTodoItem1, toDoItem1);
        Assert.assertNotNull(toDoItemService.findById(toDoItem1.getId()));

        TodoItem toDoItem2 = new TodoItem();
        toDoItem2.setItemName("Porject mapping");
        toDoItem2.setDescription("Assine HCMP project mapping");
        toDoItem2.setCreatedDate(new Date());
        TodoItem insertedTodoItem2 = toDoItemService.save(toDoItem);
        Assert.assertNotNull(insertedTodoItem2);
        Assert.assertEquals(insertedTodoItem2, toDoItem2);
        Assert.assertNotNull(toDoItemService.findById(toDoItem2.getId()));

        todoItemList.add(toDoItem);
        todoItemList.add(toDoItem1);
        todoItemList.add(toDoItem2);
    }

    @Test
    public void todoItemServiceFindAllCheck() {

        Assert.assertTrue(todoItemList.containsAll(toDoItemService.findAll()));

    }





}
