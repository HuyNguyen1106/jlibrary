/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.Book;
import com.nchtd.POJO.Payment;
import com.nchtd.POJO.PaymentDetail;
import com.nchtd.POJO.PaymentDetailPK;
import com.nchtd.POJO.Reader;
import com.nchtd.POJO.User;
import com.nchtd.services.BookService;
import com.nchtd.services.OrderService;
import com.nchtd.services.ReaderService;
import com.nchtd.services.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class CartBean implements Serializable{
    private Reader reader;
    
    /**
     * Creates a new instance of CartBean
     */
    public CartBean() {
    }
    
    @PostConstruct
    public void init(){
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        if(ctx.getSessionMap().get("cart") == null) {
            Map<Integer, Object> cart = new HashMap<>();
            
            ctx.getSessionMap().put("cart", cart);
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
    
    public List<Map<String, Object>> getCarts() {
        Map<Integer, Object> cart = (Map<Integer, Object>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
        
        List<Map<String, Object>> res = new ArrayList<>();
        for (Object value : cart.values()) {
            Map<String, Object> map = (Map<String, Object>) value;
            if(map != null)
                res.add(map);
        }
        return res;
    }
    
    

    /**
     * @return the reader
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
