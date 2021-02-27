package com.opt.common.produce;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class PersistenceContextProduce {

    @Default
    @Produces
    @Dependent
    @PersistenceContext(unitName = "todoPU", type = PersistenceContextType.TRANSACTION)
    protected EntityManager em;

}
