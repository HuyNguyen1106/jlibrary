/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.Payment;
import com.nchtd.POJO.PaymentDetail;
import com.nchtd.config.HibernateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DataService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
    
    public Map<String, Object> calculateData(int month, int year) {
        Map<String, Object> rs = new HashMap<>();
        List<Payment> pays;
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Payment> query = builder.createQuery(Payment.class);
            Root<Payment> root = query.from(Payment.class);
            
            query = query.select(root);
            
            if(month > 0 && year > 0) {
                Date from = new Date(year, month, 1);
                Date to;
                if(month == 12)
                    to = new Date(year + 1, month, 1);
                else
                    to = new Date(year, month + 1, 1);
                
                query.where(builder.between(root.get("createdAt").as(Date.class),from,to ));
               
            }
            
            Query q = session.createQuery(query);
            
            pays = q.getResultList();
        }
        long income = 0;
        for (Payment pay : pays) {
            for(PaymentDetail d : pay.getPaymentDetailList()) {
                income += d.getUnitPrice() * d.getQuantiry();
            }
        }
        rs.put("income", income);
        return rs;
    }
}
