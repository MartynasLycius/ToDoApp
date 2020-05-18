package com.vaadin.todo.test;

import com.vaadin.todo.entity.ToDoItem;
import org.junit.Assert;
import org.junit.Test;

public class ToDoItemEnttityUnitTest {

    @Test
    public void itemNameCheck() {
        String itemName = "todo1";
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setItemName(itemName);
        Assert.assertNotNull(toDoItem);
        Assert.assertEquals(toDoItem.getItemName(),itemName);
    }


}
