/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.facade;

import com.nazmul.todo.domain.TodoItem;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nazmul
 */
@Stateless
public class TodoItemFacade extends AbstractFacade<TodoItem> {

    @PersistenceContext(unitName = "TODOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TodoItemFacade() {
        super(TodoItem.class);
    }
    
    /**
     * Gets all todo items by given date
     * @param d given date
     * @return list of todo items.
     */
    public List<TodoItem> getTodosByDate(LocalDate d) {
        return em.createQuery("SELECT todos FROM TodoItem todos JOIN FETCH todos.category WHERE todos.timecreated = :date")
                .setParameter("date", d)
                .getResultList();
    }
    
    /**
     * Update the completeness of the given item
     * @param id todo item's id
     * @param complete true or false
     * @return num of rows updated
     */
    public int updateCompleteness(Long id, boolean complete) {
        int executeUpdate = em.createQuery("UPDATE TodoItem todos SET todos.done = :complete WHERE todos.id = :id")
                .setParameter("complete", complete)
                .setParameter("id", id)
                .executeUpdate();
        
        return executeUpdate;
    }

    /**
     * Gets all todos from the given dates to past given num of days
     * @param startDate start date
     * @param pastNumOfDays past num of days.
     * @return list of todo items.
     */
    public List<TodoItem> getTodosBetweenDates(LocalDate startDate, int pastNumOfDays) {
        //To include the given date
        startDate = startDate.plusDays(1);
        
        //To include the last dates
        LocalDate endDate = startDate.minusDays(pastNumOfDays + 1);
        
        return em.createQuery("SELECT todos FROM TodoItem todos JOIN FETCH todos.category WHERE todos.timecreated BETWEEN :endDate AND :startDate ORDER BY todos.timecreated")
                .setParameter("endDate", endDate)
                .setParameter("startDate", startDate)
                .getResultList();
    }

    public TodoItem findById(Long id) {
        return (TodoItem) em.createQuery("SELECT todo FROM TodoItem todo JOIN FETCH todo.category WHERE todo.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
}
