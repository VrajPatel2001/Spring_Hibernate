package com.spring_hibernate.learn.java.dataSource;

import com.spring_hibernate.learn.java.domain.Employee;
import com.spring_hibernate.learn.java.domain.Phone;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataSource {
    private static SessionFactory SESSION_FACTORY = null;

    public static SessionFactory getSessionFactory() throws HibernateException {
        if (SESSION_FACTORY == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Employee.class).addAnnotatedClass(Phone.class);
            SESSION_FACTORY = configuration.buildSessionFactory();
        }
        return SESSION_FACTORY;
    }
}
