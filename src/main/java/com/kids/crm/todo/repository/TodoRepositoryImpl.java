package com.kids.crm.todo.repository;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.utils.PersistenceManager;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class TodoRepositoryImpl implements TodoRepository {

    @PersistenceContext(name = "jpa-example")
    protected EntityManager entityManager;

    @Override
    public void createTodo(Todo todo) {
      /*  EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
                .begin();
        em.persist(todo);
        em.getTransaction()
                .commit();
        PersistenceManager.INSTANCE.close();*/
        entityManager.persist(todo);
    }

    public List<Todo> fetch() {
        Query query = entityManager.createQuery("select t from Todo t");
        return query.getResultList();
    }


    public Todo findById(Long id) {
        return entityManager.find(Todo.class, id);
    }
}
