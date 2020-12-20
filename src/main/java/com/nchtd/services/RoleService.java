/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.POJO.Role;
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
public class RoleService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
    
    public List<Role> getAll() {
        try (Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Role> query = builder.createQuery(Role.class);
            Root<Role> root = query.from(Role.class);
            
            query = query.select(root);
            
            Query q = session.createQuery(query);
            
            return q.getResultList();
        }
    }
    
    public Role getById(int id) {
        try (Session session = FACTORY.openSession()) {           
            return session.get(Role.class, id);
        }
    }
    
    public boolean addOrSave(Role o) {
        try (Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(o);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                return false;
            }            
        }
        return true;
    }
    public boolean deleteRole(Role role){
        try (Session session = FACTORY.openSession()) {
                try {
                    session.getTransaction().begin();
                    session.delete(role);
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                    return false;
                }
                return true;
            }
    }
    public Role getRoleById(int roleId){
        try (Session session = FACTORY.openSession()){
            return session.get(Role.class, roleId);
        }
    }
}
