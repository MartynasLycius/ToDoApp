package com.kids.crm.todo.repository;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    public List<Todo> fetch() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
                .begin();

        Query query = em.createQuery("select t from Todo t");
        List<Todo> todos = query.getResultList();
        em.getTransaction()
                .commit();
        em.close();
        PersistenceManager.INSTANCE.close();
        return todos;

    }
}
