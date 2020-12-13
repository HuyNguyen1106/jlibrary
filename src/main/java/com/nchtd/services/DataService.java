/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.services;

import com.nchtd.config.HibernateUtils;
import org.hibernate.SessionFactory;

/**
 *
 * @author Admin
 */
public class DataService {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();
}
