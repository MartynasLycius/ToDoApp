package com.vaadin.todo.test;

import com.vaadin.todo.entity.ToDoItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ToDoItemEntityUnitTest {

    @Test
    public void itemNameCheck() {
        String itemName = "todo1";
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setItemName(itemName);
        Assert.assertNotNull(toDoItem);
        Assert.assertEquals(toDoItem.getItemName(),itemName);
    }

    @Test
    public void descriptionCheck() {
        String description = "todo1";
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setDescription(description);
        Assert.assertNotNull(toDoItem);
        Assert.assertEquals(toDoItem.getDescription(),description);

    }

    @Test
    public void createdDateCheck() {
        Date date = new Date();
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setCreatedDate(date);
        Assert.assertNotNull(toDoItem);
        Assert.assertEquals(toDoItem.getCreatedDate(),date);

    }



}
