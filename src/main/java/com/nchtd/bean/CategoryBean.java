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
    private static final CategoryService categoryService = new CategoryService();
    private String title;
    private String description;
    
    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
    }
    
    public List<Category> getList() {
        return categoryService.getAll();
    }
    
    public String addCate() {
        Category c = new Category();
        c.setTitle(this.getTitle());
        c.setDescription(this.getDescription());
        
        if(categoryService.addOrSave(c) == true) {
            return "index?faces-redirect=true";
        }
        
        return "create";
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
