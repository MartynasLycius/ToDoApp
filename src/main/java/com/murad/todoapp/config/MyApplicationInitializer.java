package com.murad.todoapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
public class MyApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MyApplicationConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

   @Override
   public void customizeRegistration(ServletRegistration.Dynamic registration) {
       registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
   }
}