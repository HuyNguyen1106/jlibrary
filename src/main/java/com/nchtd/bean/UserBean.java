/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.User;
import com.nchtd.services.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@Named(value = "userBean")
@SessionScoped
@ManagedBean
public class UserBean implements Serializable{
    private final static UserService userService = new UserService();
    private Integer usId;
    private String username;
    private String password;
    private String email;
    private int role;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        if (!FacesContext.getCurrentInstance().isPostback()){
            String userId = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("user_id");
            if (userId != null && !userId.isEmpty()){    
                User user = userService.getUserById(Integer.parseInt(userId));
                this.usId = user.getId();
                this.username = user.getUsername();
                this.password = user.getPassword();
                this.email = user.getEmail();
                this.role = user.getRole();
            }
        }
    }
    
    public String checkAuth() {
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") == null) {
            return "login?faces-redirect=true";
        }
        return null;
    }
    
    public String checkAdminAuth() {
        if(this.checkAuth() == null) {
            User u = (User) FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap().get("user");
            if(u.getRole() == 1) {
                return null;
            }
        }
        return "login?faces-redirect=true";
    }
    
    public String checkLogin() {
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null) {
            return "index?faces-redirect=true";
        }
        return null;
    }
    
    public String checkAdmin() {
        if(this.checkLogin() != null) {
            User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            if(u.getRole() == 1) {
                return "index?faces-redirect=true";
            }
        }
        return null;
    }
    
    public String login() {
        User u = userService.login(this.username, this.password);
        if(u != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
            return "index?faces-redirect=true";
        }
        return "login";
    }
    
    public String loginAdmin() {
        User u = userService.loginAdmin(this.username, this.password);
        if(u != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminFlag", u.getRole().getName());
            return "index?faces-redirect=true";
        }
        return "login";
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }
    
    public List<User> getList() {
        return userService.getAll();
    }
    
    public String getRoleText(int role) {
        String rs = "";
        switch(role) {
            case 1:
                rs = "ADMIN";
                break;
            case 2:
                rs = "REGULAR";
                break;
        }
        return rs;
    }
    
//    public List<String> getRoleList() {
//        
//        List<String> list = new ArrayList<>();
//        int[] roles = userService.roleList();
//        list.add(roles[1], "ADMIN");
//        list.add(roles[2], "REGULAR");
//        return list;
//    }
    
    public String addUser() {
        User u ;
        if (this.getUsId() != null){
            u = userService.getUserById(this.getUsId());
        } else {
            u = new User();
        }
        u.setUsername(this.username);
        u.setPassword(this.password);
        u.setEmail(this.email);
        u.setRole(this.role);
        u.setActive(Short.parseShort("1"));
        
        if(userService.addUser(u) == true) {
            return "index?faces-redirect=true";
        }
        
        return "create";
    }
    
    public String updateUser() {
//        User u = new User();
//        u.setUsername(this.username);
//        u.setPassword(this.password);
//        u.setEmail(this.email);
//        u.setRole(this.role);
//        
//        if(userService.updateUser(u) == true) {
//            return "index?faces-redirect=true";
//        }
        
        return "create";
    }
    
    public String changeActive (User user) throws Exception{
        if(user.getActive() == 0) {
            user.setActive(Short.parseShort("1"));
        } else {
            user.setActive(Short.parseShort("0"));
        }
        if (userService.updateUser(user) == true){
            return "index?faces-redirect=true";
        }
        throw new Exception("Delete failed");
    }
    
    public String getActiveText(User u) {
        return u.getActive() == 0 ? "Disabled" : "Activated";
    }
    public String getActiveColor(User u) {
        return u.getActive() == 0 ? "text-danger" : "text-success";
    }
    /**
     * @return the userId
     */
    public int getUserId() {
        return getUsId();
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.setUsId((Integer) userId);
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
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @return the usId
     */
    public Integer getUsId() {
        return usId;
    }

    /**
     * @param usId the usId to set
     */
    public void setUsId(Integer usId) {
        this.usId = usId;
    }
}
