/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.config;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Admin
 */
public class HibernateUtils {
    private static final SessionFactory FACTORY;
    
    static {
        try {
            Configuration conf = new Configuration();
            
            // registry pojo class here
            
            Properties properties = new Properties();
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost/mcdb"); // database: jlibrary
            properties.put(Environment.USER, "root"); //username
            properties.put(Environment.PASS, "root"); //password

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(properties).build();

            FACTORY = conf.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        
    }

    /**
     * @return the FACTORY
     */
    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
