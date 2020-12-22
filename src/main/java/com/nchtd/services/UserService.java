/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.User;
import com.nchtd.config.HibernateUtils;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Admin
 */
public class UserService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
    
    public List<User> getAll() {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            
            query = query.select(root);
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
    
    public User login(String username, String password) {
        password = DigestUtils.md5Hex(password);
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            
            query = query.select(root).where(builder.and(
                    builder.equal(root.get("username").as(String.class), username),
                    builder.equal(root.get("password").as(String.class), password),
                    builder.equal(root.get("active"), 1)
            ));
            
            Query q = session.createQuery(query);
            
            return (User)q.getSingleResult();
        }
    }
    
    public User loginAdmin(String username, String password) {
        User u = login(username, password);
        if(u == null) {
            return u;
        }
        
        if(u.getRole().getName().equals("ADMIN")) {
            return u;
        }

        return null;
        
    }
    
    public User getById(int id) {
        try (Session session = FACTORY.openSession()) {
            return session.get(User.class, id);
        }
    }
    
    public boolean addUser(User u) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                u.setPassword(DigestUtils.md5Hex(u.getPassword()));
                u.setCreatedAt(new Date());
                session.save(u);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }
            
        }
        return true;
    }
    
    public boolean updateUser(User u) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                u.setUpdatedAt(new Date());
                session.update(u);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }
            
        }
        return true;
    }
}
