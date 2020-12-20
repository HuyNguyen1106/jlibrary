/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@Named(value = "cartBean")
@SessionScoped
@ManagedBean
public class CartBean {

    /**
     * Creates a new instance of CartBean
     */
    public CartBean() {
    }
    
    @PostConstruct
    public void init(){
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        if(ctx.getSessionMap().get("cart") == null) {
            ctx.getSessionMap().put("cart", new HashMap<>());
        }
    }
    
    public String addToCart(Integer bookId, String bookTitle, long price) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        Map<Integer, Object> cart = (Map<Integer, Object>) ctx.getSessionMap().get("cart");
        if (cart.get(bookId) == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("bookId", bookId);
            map.put("bookTitle", bookTitle);
            map.put("price", price);
            map.put("qty", 1);
            cart.put(bookId, map);
        } else {
            Map<String, Object> map = (Map<String, Object>) cart.get(bookId);
            map.put("qty", Integer.parseInt(map.get("qty").toString()) + 1);
        }
        return "Successfully add to cart";
    }
    
}
