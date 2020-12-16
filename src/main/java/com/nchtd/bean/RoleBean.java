/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.Role;
import com.nchtd.services.RoleService;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Admin
 */
@Named(value = "roleBean")
@RequestScoped
@ManagedBean
public class RoleBean {
    public static final RoleService roleService = new RoleService();
    private String name;
    /**
     * Creates a new instance of RoleBean
     */
    public RoleBean() {
    }
    
    public List<Role> getRoles() {
        return roleService.getAll();
    }
    
    public String addRole() {
        Role obj = new Role();
        obj.setName(this.name);
        
        if(roleService.addOrSave(obj) == true) {
            return "index?faces-redirect=true";
        }
        
        return "create";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
