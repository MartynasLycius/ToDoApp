/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.view;

import com.nazmul.todo.constant.CategoryType;
import com.nazmul.todo.model.CategoryCounterModel;
import com.vaadin.icons.VaadinIcons;
import java.time.LocalDate;

/**
 *
 * @author nazmul
 */
public interface ITodosComponent {
    /**
     * Creates and adds category layout for each category in todos view.
     * @param name name of the category
     * @param total total count of todos of the category
     * @param completed total count of completed todos of the category
     * @param type category type {@code CategoryType.class}
     */
    void addCategoryLayout(String name, Long total, Long completed, CategoryType type);
    
    /**
     * Creates and adds layout for each todo item in todos view.
     * @param id id of the todo item
     * @param name name of the todo item
     * @param description description of the todo item
     * @param completed boolean which defines the completeness of the todo item
     * @param type category type {@code CategoryType.class}
     */
    void addTodoLayout(Long id, String name, String description, boolean completed, CategoryType type);
}