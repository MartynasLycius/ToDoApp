package com.eh.todo.util;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
/**
 * @author Md. Emran Hossain<emranhos1@gmail.com>
 * @version 1.0.00.EH
 * @since 1.0.00.EH
 */
@Component
public class HibernateSessionUtil {

    public SessionFactory getSessionFactory(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
          throw new NullPointerException("factory is not a hibernate factory");
        }
        return factory.unwrap(SessionFactory.class);
      }
}
