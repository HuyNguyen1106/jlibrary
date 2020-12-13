/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.User;
import com.nchtd.services.UserService;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Admin
 */
@Named(value = "userBean")
@RequestScoped
@ManagedBean
public class UserBean {

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
    public List<User> getList() {
        return UserService.getAll();
    }
}
