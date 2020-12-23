/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.Book;
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
public class BookService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
    
    public List<Book> getAll(Category cate, String keyword) {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            
            query = query.select(root).where(builder.equal(root.get("active"), 1));
            if (cate != null){
                query.where(builder.equal(root.get("categoryId").as(Category.class), cate));
            }
            if(keyword != null && !keyword.isEmpty()){
                query.where(builder.or(builder.like(root.get("title").as(String.class), keyword), builder.like(root.get("author").as(String.class), keyword)));
            }
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
    public Book getById(int id) {
        try (Session session = FACTORY.openSession()) {
            return session.get(Book.class, id);
        }
    }
    public boolean addOrSave(Book b) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(b);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    public Book getBookById(int bookId){
        try (Session session = FACTORY.openSession()){
            return session.get(Book.class, bookId);
        }
    }
}
