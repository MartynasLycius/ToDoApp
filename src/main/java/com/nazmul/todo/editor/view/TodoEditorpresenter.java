/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.editor.view;

import com.nazmul.todo.domain.Category;
import com.nazmul.todo.domain.TodoItem;
import com.nazmul.todo.facade.CategoryFacade;
import com.nazmul.todo.facade.TodoItemFacade;
import com.nazmul.todo.service.NavigationEvent;
import com.vaadin.cdi.UIScoped;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author nazmul
 */
@UIScoped
public class TodoEditorpresenter implements ITodoEditorPresenter{

    @Inject
    private TodoItemFacade itemFacade;
    
    @Inject
    private CategoryFacade categoryFacade;
    
    @Inject
    private javax.enterprise.event.Event<NavigationEvent> navigationEvent;
    
    private TodoItem todoItem;
    
    @Override
    public TodoItem getToDoItem(String id) {
        todoItem = StringUtils.isBlank(id) ? new TodoItem() 
                : itemFacade.findById(Long.valueOf(id));
        
        return todoItem;
    }

    @Override
    public List<Category> getCategories() {
        return categoryFacade.findAll();
    }

    @Override
    public void navigateTo(String viewId) {
        navigationEvent.fire(new NavigationEvent(viewId));
    }

    @Override
    public void saveItem(TodoItem bean) {
        
        if(bean.getId() == null) {
            itemFacade.create(bean);
        } else {
            itemFacade.edit(bean);
        }
    }

    @Override
    public TodoItem getToDoItem() {
        return todoItem;
    }
    
}
