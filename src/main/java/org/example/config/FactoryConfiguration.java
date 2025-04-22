package org.example.config;

import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private static SessionFactory sessionFactory;

    private FactoryConfiguration() throws IOException {
        Configuration configuration=new Configuration();

        Properties property = new Properties();
        property.load(Thread.currentThread().getContextClassLoader().
                getResourceAsStream("hibernate.properties"));

        configuration.setProperties(property);
        configuration.addAnnotatedClass(Payments.class).
                addAnnotatedClass(Patients.class).
                addAnnotatedClass(Users.class).
                addAnnotatedClass(Therapists.class).
                addAnnotatedClass(TherapySessions.class).
                addAnnotatedClass(TherapyPrograms.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration==null)?factoryConfiguration=new FactoryConfiguration():
                factoryConfiguration;
    }

    public Session openSession(){
        return sessionFactory.openSession();
    }
}
