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
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@Named(value = "categoryBean")
@RequestScoped
@ManagedBean
public class CategoryBean {
    private static final CategoryService categoryService = new CategoryService();
    private Integer ctId;
    private String title;
    private String description;
    
    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
            if (!FacesContext.getCurrentInstance().isPostback()){
            String cateId = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("cate_id");
            if (cateId != null && !cateId.isEmpty()){    
                Category cate = categoryService.getCategoryById(Integer.parseInt(cateId));
                this.ctId = cate.getId();
                this.title = cate.getTitle();
                this.description = cate.getDescription();
            }
        }
    }
    public List<Category> getList() {
        return categoryService.getAll();
    }
    
    public String addCate() {
        Category c ;
        if (this.getCtId() != null){
            c = categoryService.getCategoryById(this.getCtId());
        } else {
            c = new Category(); 
        }         
        c.setTitle(this.getTitle());
        c.setDescription(this.getDescription());
        c.setActive(Short.parseShort("1"));
        
        if(categoryService.addOrSave(c) == true) {
            return "index?faces-redirect=true";
        }   
        return "create";
    }
    public String deleteCategory (Category cate) throws Exception{
        cate.setActive(Short.parseShort("0"));
        if (categoryService.addOrSave(cate) == true){
            return "index?faces-redirect=true";
        }
        throw new Exception("Delete failed");
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

    /**
     * @return the ctId
     */
    public Integer getCtId() {
        return ctId;
    }

    /**
     * @param ctId the ctId to set
     */
    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }
}
