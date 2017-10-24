package com.ksfe.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionUtil {
    @Autowired
    private static SessionFactory sessionFactory;
    static Session session;

    public static Session getSession() {
        if (session == null) {
            session = sessionFactory.getCurrentSession();
        }
        return session;

    }

}
