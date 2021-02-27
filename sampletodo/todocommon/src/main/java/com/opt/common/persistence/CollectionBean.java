package com.opt.common.persistence;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;


import com.opt.common.enumeration.OrderType;
import com.opt.exception.NoDataException;

@ApplicationScoped
@Transactional(value = TxType.SUPPORTS)
public class CollectionBean extends CollectionQuery {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public <T> int count(final Class<T> entityClass) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<T> rt = cq.from(entityClass);

        cq.select(cb.count(rt));
        final Query query = em.createQuery(cq);
        return ((Long) query.getSingleResult()).intValue();
    }

    public <T> T find(final Class<T> entityClass, final Object id) throws NoDataException {
        final T entity = em.find(entityClass, id);

        if (entity != null) {
            return entity;
        } else {
            throw new NoDataException();
        }
    }

    public <T> List<T> getAll(final Class<T> entityClass) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(entityClass);
        final Root<T> rt = cq.from(entityClass);

        cq.select(rt);
        final TypedQuery<T> query = em.createQuery(cq);
        return query.getResultList();
    }

    public <T> List<T> getRange(final Class<T> entityClass, final int first, final int pageSize, final String sortField, final String sortOrder) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(entityClass);
        final Root<T> rt = cq.from(entityClass);

        cq.select(rt);
        Order order = null;
        final Expression<T> expression = rt.get(sortField);

        if (sortOrder.equalsIgnoreCase(OrderType.ASCENDING.name())) {
            order = cb.asc(expression);
        } else if (sortOrder.equalsIgnoreCase(OrderType.DESCENDING.name())) {
            order = cb.desc(expression);
        }

        cq.orderBy(order);
        final TypedQuery<T> query = em.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public <T> T uniqueResult(final Class<T> entityClass, final String columnName, final Object columnValue) throws NoDataException {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(entityClass);
        final Root<T> rt = cq.from(entityClass);
        final ParameterExpression pe = cb.parameter(columnValue.getClass());
        final Expression<Boolean> ex = cb.equal(rt.get(columnName), pe);
        cq.select(rt).where(ex);

        try {
            final TypedQuery<T> query = em.createQuery(cq);
            query.setParameter(pe, columnValue);
            return query.getSingleResult();
        } catch (final NoResultException nre) {
            throw new NoDataException();
        }
    }

}
