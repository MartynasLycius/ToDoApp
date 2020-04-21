package com.kids.crm.todo.repository;

import com.kids.crm.todo.model.Todo;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void delete(Todo todo) {
        entityManager.remove(todo);
    }


    public Optional<Todo> findById(Long id) {
        return Optional.of(entityManager.find(Todo.class, id));
    }

    @Override
    public Todo update(Todo todo) {
        return entityManager.merge(todo);
    }
}
