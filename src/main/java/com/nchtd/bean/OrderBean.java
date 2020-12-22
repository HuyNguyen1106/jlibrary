/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.BookOrder;
import com.nchtd.POJO.Reader;
import com.nchtd.services.OrderService;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Admin
 */
@Named(value = "orderBean")
@RequestScoped
@ManagedBean
public class OrderBean {
    private Reader reader;
    private static final OrderService service = new OrderService();
    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean() {
    }
    
    public List<BookOrder> getOrders(boolean isNotDone) {
        return service.getAll(isNotDone);
    }
    
    public String end(BookOrder order) {
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
}
