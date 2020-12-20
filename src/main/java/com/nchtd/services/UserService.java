/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.User;
import com.nchtd.config.HibernateUtils;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            
            query = query.select(root).where(builder.equal(root.get("active"), Short.parseShort("1")));
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
    
    public User getById(int id) {
        try (Session session = FACTORY.openSession()) {
            return session.get(User.class, id);
        }
    }
    
    public boolean addOrSave(User u) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(u);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }
            
        }
        return true;
    }
}
