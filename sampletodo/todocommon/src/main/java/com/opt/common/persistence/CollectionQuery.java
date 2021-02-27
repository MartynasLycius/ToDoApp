package com.opt.common.persistence;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.opt.exception.NoDataException;


public abstract class CollectionQuery {

    protected abstract EntityManager getEntityManager();

    public int persistenceCount(final String queryString, final Map<String, Object> params) {
        final Query query = getEntityManager().createQuery(queryString);
        return count(query, params);
    }

    public <T> T persistenceFind(final Class<T> entityClass, final String queryString, final Map<String, Object> params) throws NoDataException {
        try {
            final TypedQuery<T> query = persistenceQuery(entityClass, queryString, params);
            return query.getSingleResult();
        } catch (final NoResultException ex) {
            throw new NoDataException();
        }
    }

    public <T> List<T> persistenceList(final Class<T> entityClass, final String queryString, final Map<String, Object> params) {
        final TypedQuery<T> query = persistenceQuery(entityClass, queryString, params);
        return query.getResultList();
    }

    public <T> List<T> persistenceRange(final Class<T> entityClass, final String queryString, final Map<String, Object> params, final int first, final int pageSize) {
        final TypedQuery<T> query = persistenceQuery(entityClass, queryString, params);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public int namedCount(final String name, final Map<String, Object> params) {
        final Query query = getEntityManager().createNamedQuery(name);
        return count(query, params);
    }

    public <T> T namedFind(final Class<T> entityClass, final String name, final Map<String, Object> params) throws NoDataException {
        try {
            final TypedQuery<T> query = namedQuery(entityClass, name, params);
            return (T) query.getSingleResult();
        } catch (final NoResultException ex) {
            throw new NoDataException();
        }
    }

    public <T> List<T> namedList(final Class<T> entityClass, final String name, final Map<String, Object> params) {
        final TypedQuery<T> query = namedQuery(entityClass, name, params);
        return query.getResultList();
    }

    public <T> List<T> namedRange(final Class<T> entityClass, final String name, final Map<String, Object> params, final int first, final int pageSize) {
        final TypedQuery<T> query = namedQuery(entityClass, name, params);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public int nativeCount(final String queryString, final Map<String, Object> params) {
        final Query query = getEntityManager().createNativeQuery(queryString);
        return count(query, params);
    }

    public <T> T nativeFind(final Class<T> entityClass, final String queryString, final Map<String, Object> params) throws NoDataException {
        try {
            final Query query = nativeQuery(entityClass, queryString, params);
            return (T) query.getSingleResult();
        } catch (final NoResultException ex) {
            throw new NoDataException();
        }
    }

    public <T> List<T> nativeList(final Class<T> entityClass, final String queryString, final Map<String, Object> params) {
        final Query query = nativeQuery(entityClass, queryString, params);
        return query.getResultList();
    }

    public <T> List<T> nativeRange(final Class<T> entityClass, final String queryString, final Map<String, Object> params, final int first, final int pageSize) {
        final Query query = nativeQuery(entityClass, queryString, params);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public <T> List<T> getOptionList(final Class<T> entityClass, final String name) {
        Query query = getEntityManager().createNamedQuery(name);
        return query.getResultList();
    }

    private int count(Query query, final Map<String, Object> params) {
        query = prepareQuery(query, params);
        return ((Long) query.getSingleResult()).intValue();
    }

    private <T> TypedQuery<T> persistenceQuery(final Class<T> entityClass, final String queryString, final Map<String, Object> params) {
        final TypedQuery<T> query = getEntityManager().createQuery(queryString, entityClass);
        return prepareQuery(query, params);
    }

    private <T> TypedQuery<T> namedQuery(final Class<T> entityClass, final String name, final Map<String, Object> params) {
        final TypedQuery<T> query = getEntityManager().createNamedQuery(name, entityClass);
        return prepareQuery(query, params);
    }

    private <T> Query nativeQuery(final Class<T> entityClass, final String queryString, final Map<String, Object> params) {
        final Query query = getEntityManager().createNativeQuery(queryString, entityClass);
        return prepareQuery(query, params);
    }

    private TypedQuery prepareQuery(final TypedQuery query, final Map<String, Object> params) {
        final Set<Entry<String, Object>> entries = params.entrySet();
        final Stream<Entry<String, Object>> stream = entries.parallelStream();
        stream.forEach(param -> query.setParameter(param.getKey(), param.getValue()));
        return query;
    }

    private Query prepareQuery(final Query query, final Map<String, Object> params) {
        final Set<Entry<String, Object>> entries = params.entrySet();
        final Stream<Entry<String, Object>> stream = entries.parallelStream();
        stream.forEach(param -> query.setParameter(param.getKey(), param.getValue()));
        return query;
    }

}
