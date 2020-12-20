/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.Role;
import com.nchtd.services.RoleService;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@Named(value = "roleBean")
@RequestScoped
@ManagedBean
public class RoleBean {
    public static final RoleService roleService = new RoleService();
    private Integer roleId;
    private String name;
    /**
     * Creates a new instance of RoleBean
     */
    public RoleBean() {
        if (!FacesContext.getCurrentInstance().isPostback()){
            String rolesId = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("role_id");
            if (rolesId != null && !rolesId.isEmpty()){    
                Role role = roleService.getRoleById(Integer.parseInt(rolesId));
                this.roleId = role.getId();
                this.name = role.getName();
            }
        }
    }
    
    public List<Role> getRoles() {
        return roleService.getAll();
    }
    
    public String addRole() {
        Role o;
        if (this.getRoleId() != null){
            o = roleService.getRoleById(this.getRoleId());
        } else {
            o = new Role(); 
        }         
        o.setName(this.name);
        if(roleService.addOrSave(o) == true) {
            return "index?faces-redirect=true";
        }
        return "create";
    }
    
    public String deleteRole (Role role) throws Exception{
        //role.setDeletedAt(new Date());
        if (roleService.deleteRole(role) == true){
            return "successful";
        }
        throw new Exception("Delete failed");
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

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
}
