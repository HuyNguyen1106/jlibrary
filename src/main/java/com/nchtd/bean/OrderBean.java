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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@Named(value = "orderBean")
@RequestScoped
@ManagedBean
public class OrderBean {
    private Reader reader;
    private int orderId;
    private static final BookService bookService = new BookService();
    private static final OrderService service = new OrderService();
    private int qty;
    private long price;
    private Book book;
    private Payment payment;
    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap().get("order_id");
        if(id != null && !id.isEmpty()) {
            this.orderId = Integer.parseInt(id);
        }
    }
    
    public List<Payment> getOrders() {
        return service.getAll();
    }
    
    public List<PaymentDetail> getDetails() {
        return service.getDetailsById(this.orderId);
    }
    
    public String saveOrder() {
        Map<Integer, Object> cart = (Map<Integer, Object>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
        if(cart != null) {
            Payment o = new Payment();
            Date date = new Date();
            o.setCreatedAt(date);
            o.setUpdatedAt(date);
            User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            
            o.setUserId(u);
            
            o.setReaderId(this.reader);
            List<PaymentDetail> details = new ArrayList<>();
            for (Object value : cart.values()) {
                Map<String, Object> map = (Map<String, Object>) value;
                Book b = bookService.getById((int) map.get("bookId"));
                
                PaymentDetail od = new PaymentDetail();
                od.setPayment(o);
                od.setBook(b);
                od.setQuantiry((int) map.get("qty"));
                od.setUnitPrice((long) map.get("price"));
                details.add(od);
            }
            if(service.saveOrder(o,details) == true) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("cart");
                return "index?faces-redirect=true"; 
            }
        }
        
        return "cart";
    }
    
    public String end(Payment order) {
        return "";
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

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
