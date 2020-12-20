/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.Reader;
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
public class ReaderService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
    
    public List<Reader> getAll() {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Reader> query = builder.createQuery(Reader.class);
            Root<Reader> root = query.from(Reader.class);
            
            query = query.select(root).where(builder.isNull(root.get("deletedAt")));
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
        
    }   
    public boolean addOrSave(Reader reader) {
            try (Session session = FACTORY.openSession()) {
                try {
                    session.getTransaction().begin();
                    session.saveOrUpdate(reader);
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                    e.printStackTrace();
                    return false;
                }            
            }
            return true;
        }
    public boolean deleteReader (Reader reader){
        try (Session session = FACTORY.openSession()) {
                try {
                    session.getTransaction().begin();
                    session.delete(reader);
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                    return false;
                }
                return true;
            }
    }
    public Reader getReaderById(int readerId){
        try (Session session = FACTORY.openSession()){
            return session.get(Reader.class, readerId);
        }
    }
}
