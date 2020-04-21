package com.kids.crm.todo.repository;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.utils.PersistenceManager;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class TodoRepositoryImpl implements TodoRepository{

    @PersistenceContext(name = "jpa-example")
    protected EntityManager entityManager;


    public void createTodo(Todo todo){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
                .begin();
        em.persist(todo);
        em.getTransaction()
                .commit();
        PersistenceManager.INSTANCE.close();
    }

    public List<Todo> fetch() {
        /*EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
                .begin();

        Query query = em.createQuery("select t from Todo t");
        List<Todo> todos = query.getResultList();
        em.getTransaction()
                .commit();
        PersistenceManager.INSTANCE.close();
        return todos;*/
        Query query = entityManager.createQuery("select t from Todo t");
        List<Todo> todos = query.getResultList();
        return todos;

    }
}
