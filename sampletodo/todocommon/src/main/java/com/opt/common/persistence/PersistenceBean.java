/*****************************************************************************************************************
 *
 *	 File			 : PersistenceBean.java
 *
  *****************************************************************************************************************/


package com.opt.common.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.opt.exception.BusinessException;


/*
 * This class is  PersistenceBean class   which is used for
 *  manipulate data into  db
 */
@ApplicationScoped
@Transactional(value = TxType.MANDATORY, rollbackOn = BusinessException.class)
public class PersistenceBean extends SequenceStoreGenerator {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void save(final Object entity) {
        em.persist(entity);
        em.flush();
    }

    public void update(final Object entity) {
        em.merge(entity);
        em.flush();
    }

    public void delete(final Object entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.flush();
    }

    public <T> void delete(final Class<T> entityClass, final Object id) {
        delete(em.find(entityClass, id));
    }

}
