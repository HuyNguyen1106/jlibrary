/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.Role;
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
    private final static UserService userService = new UserService();
    private int userId;
    private String username;
    private String password;
    private String email;
    private Role role;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
    public List<User> getList() {
        return userService.getAll();
    }
    
    public String addUser() {
        User u = new User();
        u.setUsername(this.username);
        u.setPassword(this.password);
        u.setEmail(this.email);
        u.setRole(this.role);
        
        if(userService.addOrSave(u) == true) {
            return "index?faces-redirect=true";
        }
        
        return "create";
    }
    public String delete (User user) throws Exception{
        user.setActive(Short.parseShort("0"));
        if (userService.addOrSave(user) == true){
            return "successful";
        }
        throw new Exception("Delete failed");
    }
    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
