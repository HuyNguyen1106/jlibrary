/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.config;

import com.nchtd.POJO.Book;
import com.nchtd.POJO.Payment;
import com.nchtd.POJO.Category;
import com.nchtd.POJO.PaymentDetail;
import com.nchtd.POJO.User;
import com.nchtd.POJO.Reader;
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
            conf.addAnnotatedClass(Category.class);
            conf.addAnnotatedClass(Book.class);
            conf.addAnnotatedClass(Reader.class);
            conf.addAnnotatedClass(User.class);
            conf.addAnnotatedClass(Payment.class);
            conf.addAnnotatedClass(PaymentDetail.class);
            
            Properties properties = new Properties();
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost/jlibrary"); // database: jlibrary
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
