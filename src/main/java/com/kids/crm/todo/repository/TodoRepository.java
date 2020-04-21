package com.kids.crm.todo.repository;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.utils.PersistenceManager;

import javax.persistence.EntityManager;

public class TodoRepository {
    public void createTodo(Todo todo){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
                .begin();
        em.persist(todo);
        em.getTransaction()
                .commit();
        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
