/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.view;

import com.nazmul.todo.constant.CategoryType;
import com.nazmul.todo.model.CategoryCounterModel;
import com.nazmul.todo.domain.TodoItem;
import com.nazmul.todo.facade.CategoryFacade;
import com.nazmul.todo.service.NavigationEvent;
import com.nazmul.todo.facade.TodoItemFacade;
import com.nazmul.todo.model.CategoryStream;
import com.nazmul.todo.model.TodosStream;
import com.vaadin.cdi.UIScoped;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 *
 * @author nazmul
 */
@UIScoped
public class TodosPresenter implements ITodosPresenter{
    
    @Inject
    private TodoItemFacade itemFacade;
    
    @Inject
    private CategoryFacade categoryFacade;
    
    @Inject
    private javax.enterprise.event.Event<NavigationEvent> navigationEvent;
    
    private ITodosComponent viewComponent;
    
    private List<TodoItem> todos;

    @Override
    public void navigateTo(String viewId, Long itemId) {
        navigationEvent.fire(new NavigationEvent(viewId, itemId));
    }

    @Override
    public void setView(ITodosComponent component) {
        this.viewComponent = component;
    }

    @Override
    public void generateTodoCategoriesSection(LocalDate date) {
        todos = TodosStream.of(itemFacade.getTodosByDate(date)).orderByCategoryId().todos();
        
        //gets all available categories and convert them to a helper model with sepcific counters based on todos.
        List<CategoryCounterModel> categoryViewModels = CategoryStream
                .of(categoryFacade.findAll())
                .addCategoryTypeAll()
                .orderedById()
                .convertToCategoryCounterModel(todos);
        
        for(CategoryCounterModel cv : categoryViewModels) {
            viewComponent.addCategoryLayout(cv.getType().getKey().toUpperCase(),
                    cv.getTotal(),
                    cv.getCompletedCount(),
                    cv.getType());
        }
    }

    @Override
    public void generateTodosSection() {
        addTodoLayouts(todos);
    }

    @Override
    public void generateTodosSectionFilteredbyCategory(CategoryType type) {
        List<Long> allowedCategoryIds = CategoryType.allowedCategoryIds(type);
        
        List<TodoItem> filteredTodos = TodosStream
                .of(todos)
                .ofCategories(allowedCategoryIds)
                .todos();
        
        addTodoLayouts(filteredTodos);
    }
    
    /**
     * Creates and adds todo layout for each todos.
     * @param todos list of todos
     */
    private void addTodoLayouts(List<TodoItem> todos) {
        
        for(TodoItem todo : todos) {
            viewComponent.addTodoLayout(
                    todo.getId(),
                    todo.getName(),
                    todo.getDescription(),
                    todo.getDone(),
                    CategoryType.findById(todo.getCategory().getId()));
        }
    }

    @Override
    public void removeTodoItem(Long id) {
        itemFacade.remove(itemFacade.find(id));
    }

    @Override
    public void updateCompleteness(Long id, Boolean complete) {
        itemFacade.updateCompleteness(id, complete);
    }

    @Override
    public String getCategoryLayoutStyle(Long typeId) {
        if(Objects.equals(typeId, CategoryType.All.getId())) {
            return "category-all-color";
        } else if(Objects.equals(typeId, CategoryType.WORK.getId())) {
            return "category-work-color";
        } else if(Objects.equals(typeId, CategoryType.HOME.getId())) {
            return "category-home-color";
        } else {
            return "category-personal-color";
        }
    }
}
