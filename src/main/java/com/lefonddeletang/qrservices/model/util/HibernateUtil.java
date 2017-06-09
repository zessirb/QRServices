package com.lefonddeletang.qrservices.model.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton de session Hibernate
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Configuration d'une session Hibernate
     * 
     * @return Session Hibernate
     */
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}