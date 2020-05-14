package com.todo.shantanu.service;

import com.todo.shantanu.entity.Todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;

@Transactional
public class TodoService {
    private static final EntityManagerFactory factory =
            createEntityManagerFactory("todoUnit");

    private static EntityManager em;

    public void save(Todo todo) {
        begin();
        em.persist(todo);
        commit();
    }

    public void update(Todo todo) {
        em.merge(todo);
        em.flush();
        commit();
    }

    private void commit() {
        em.getTransaction().commit();
    }

    private void begin() {
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public void deleteById(Long id) {
        begin();
        Todo todo = findById(id);
        em.remove(em.contains(todo) ? todo : em.merge(todo));
        em.flush();
        commit();
    }

    public List<Todo> findAllTodos() {
        begin();
        return em.createQuery("from Todo", Todo.class).getResultList();
    }

    private void end() {
        em.close();
        factory.close();
    }
}
