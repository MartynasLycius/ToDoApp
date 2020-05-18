/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.view;

import com.nazmul.todo.constant.CategoryType;
import java.time.LocalDate;

/**
 *
 * @author nazmul
 */
public interface ITodosPresenter {
    
    /**
     * Generates the category section by getting all available categories with corresponding
     * todos item counter for each category by the given date.
     * @param date the given date of which all the todos will be fetched.
     */
    public void generateTodoCategoriesSection(LocalDate date);
    
    /**
     * Generates the todos section with all todos of a particular date.
     */
    public void generateTodosSection();

    /**
     * Navigates to another view.
     * @param viewId id of the view where to navigate
     * @param itemId additional todo item id to be passed with navigation as uri param.
     */
    public void navigateTo(String viewId, Long itemId);
    
    /**
     * Sets the Todos view's components
     * @param component 
     */
    public void setView(ITodosComponent component);

    /**
     * Generates todos section with all todos item of the given category type
     * @param type category type {@see com.nazmul.todo.CategoryType}
     */
    public void generateTodosSectionFilteredbyCategory(CategoryType type);

    /**
     * Removes todo item of the given id from db
     * @param id id of a todo item
     */
    public void removeTodoItem(Long id);

    /**
     * Updates the complete or incomplete property of the todo of the given id.
     * @param id id of a todo item
     * @param complete
     * @param value 
     */
    public void updateCompleteness(Long id, Boolean complete);
    
    /**
     * Gets the style String based of the category type id.
     * @param typeId id of a category type {@see com.nazmul.todo.CategoryType}
     * @return style name 
     */
    public String getCategoryLayoutStyle(Long typeId);
}
