/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.Category;
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
public class CategoryService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
    
    public List<Category> getAll() {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<Category> root = query.from(Category.class);
            
            query = query.select(root).where(builder.equal(root.get("active"), 1));
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
    
    public Category getById(int id) {
        try (Session session = FACTORY.openSession()) {            
            return session.get(Category.class, id);
        }
    }
    
    public boolean addOrSave(Category cate) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(cate);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }            
        }
        return true;
    }
    public boolean deleteCate(Category cate) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.delete(cate);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }            
        }
        return true;
    }
    public Category getCategoryById(int cateId){
        try (Session session = FACTORY.openSession()){
            return session.get(Category.class, cateId);
        }
    }
}
