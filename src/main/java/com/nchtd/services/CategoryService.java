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
    
    public static List<Category> getAll() {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<Category> root = query.from(Category.class);
            
            query = query.select(root);
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
}
