package com.vaadin.zahid.service;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.zahid.dao.ITaskRepository;
import com.vaadin.zahid.entity.Task;
import com.vaadin.zahid.dto.ItemLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@SpringComponent
@UIScope
public class ItemListLayoutService extends VerticalLayout implements IChangeItemListenerService,CrudListener<Task> {
    @Autowired
    ITaskRepository repository;

    @PostConstruct
    void init() {
        update();
    }

    private void update() {
        setTodos(repository.findAll());
    }

    private void setTodos(List<Task> all) {
        removeAll();
        all.forEach(todo -> add(new ItemLayout(todo, this)));
    }
    public int countCompleted() {
        Long count=repository.count();
        return count.intValue() ;
    }

    public void deleteCompleted() {
        repository.deleteAll();
        update();
    }


    public void addTask(Task todo) {
        repository.save(todo);
        update();
    }

    @Override
    public void itemChanged(Task task) {
        addTask(task);
    }

    @Override
    public Collection<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task add(Task task) {
       return repository.save(task);
    }

    @Override
    public Task update(Task task) {
        return repository.save(task);
    }

    @Override
    public void delete(Task task) {
         repository.delete(task);
    }
}
