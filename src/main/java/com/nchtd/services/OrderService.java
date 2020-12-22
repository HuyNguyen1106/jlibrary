/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.BookOrder;
import com.nchtd.POJO.OrderDetail;
import com.nchtd.config.HibernateUtils;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;

/**
 *
 * @author Admin
 */
public class OrderService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
    
    public List<BookOrder> getAll(boolean isNotDoneFilter) {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<BookOrder> root = query.from(BookOrder.class);
            
            query = query.select(root);
            
            if(isNotDoneFilter) {
                query.where(builder.or(builder.isNull(root.get("returnDate").as(Date.class)), builder.greaterThan(root.get("returnDate").as(Date.class), new Date())));
            }
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
    
    public List<OrderDetail> getDetailsById(int id) {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<OrderDetail> root = query.from(OrderDetail.class);
        
            query = query.select(root).where(builder.equal(root.get("orderId"), id));     
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
    
    public boolean saveOrder(BookOrder order, List<OrderDetail> details) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
//                session.save(order);
                session.save(order);
                for(OrderDetail od : details) {
                    session.save(od);
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
                return false;
            }
            
            return false;
        }
    }
}
