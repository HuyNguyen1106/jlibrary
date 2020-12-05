/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.Category;
import com.nchtd.services.CategoryService;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Admin
 */
@Named(value = "categoryBean")
@RequestScoped
@ManagedBean
public class CategoryBean {

    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
    }
    
    public List<Category> getList() {
        return CategoryService.getAll();
    }
    
}
