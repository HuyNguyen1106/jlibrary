/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.Payment;
import com.nchtd.POJO.PaymentDetail;
import com.nchtd.POJO.PaymentDetailPK;
import com.nchtd.config.HibernateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Admin
 */
public class OrderService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
    
    public List<Payment> getAll(boolean done) {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Payment> query = builder.createQuery(Payment.class);
            Root<Payment> root = query.from(Payment.class);
            
            query = query.select(root).orderBy(builder.desc(root.get("returnDate")));
            if(done) {
                query.where(builder.isNull(root.get("returnDate")));
            }
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
    
    public List<PaymentDetail> getDetailsById(int id) {
        if (id > 0) {
            try (Session session = FACTORY.openSession()) {
                Payment p = session.get(Payment.class, id);

                return p.getPaymentDetailList();
            }
        }
        return null;
    }
    
    public boolean saveOrder(Payment order, List<PaymentDetail> details) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.save(order);
                for(PaymentDetail od : details) {
                    PaymentDetailPK pk = new PaymentDetailPK();
                    pk.setBookId(od.getBook().getId());
                    pk.setPaymentId(order.getId());
                    od.setPaymentDetailPK(pk);
                    session.save(od);
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
                return false;
            }
            
            return true;
        }
    }
    public Payment getById(int id) {
        try (Session session = FACTORY.openSession()) {
            return session.get(Payment.class, id);
        }
    }
}
