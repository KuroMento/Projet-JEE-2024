package fr.cyu.jee;

import fr.cyu.jee.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Subject.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Grade.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Administrator.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch(Throwable ex) {
            System.err.println("Initial SessionFactoryCreation failed. "+ ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

