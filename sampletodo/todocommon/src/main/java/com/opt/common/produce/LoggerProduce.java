package com.opt.common.produce;

import java.lang.reflect.Member;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

public class LoggerProduce {

    @Produces
    @Dependent
    protected Logger getLogger(final InjectionPoint point) {
        final Member member = point.getMember();
        final Class<?> clazz = member.getDeclaringClass();
        return Logger.getLogger(clazz);
    }

}
