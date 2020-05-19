/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.editor.view;

import com.nazmul.todo.domain.Category;
import com.nazmul.todo.domain.TodoItem;
import java.util.List;

/**
 *
 * @author nazmul
 */
public interface ITodoEditorPresenter {
    /**
     * Gets the todo item of the given id from db
     * @param id given id
     * @return 
     */
    public TodoItem getToDoItem(String id);
    
    /**
     * Gets the already fetched todo item
     * @param id given id
     * @return 
     */
    public TodoItem getToDoItem();
    
    /**
     * Gets all categories
     * @return list of categories
     */
    public List<Category> getCategories();
    
    /**
     * Navigates to a view based on the given id.
     * @param viewId given view's id to navigate.
     */
    public void navigateTo(String viewId);

    public void saveItem(TodoItem bean);
}
