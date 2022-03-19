package com.opt.common.produce;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Member;
import java.util.Properties;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class QueryStringProduce {

    @Produces
    @Dependent
    protected Properties queryString(final InjectionPoint point) throws IOException {
        final Member member = point.getMember();
        final Class<?> clazz = member.getDeclaringClass();
        final Package daoPackage = clazz.getPackage();
        final String packageName = daoPackage.getName();
        final String fileName = packageName.substring(packageName.lastIndexOf('.') + 1);
        final String file = "query/" + fileName + ".properties";
        final Thread thread = Thread.currentThread();
        final ClassLoader classLoader = thread.getContextClassLoader();
        final InputStream inputStream = classLoader.getResourceAsStream(file);
        final Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

}
