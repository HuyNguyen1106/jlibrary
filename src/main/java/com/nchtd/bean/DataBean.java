/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.services.DataService;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Admin
 */
@Named(value = "dataBean")
@RequestScoped
@ManagedBean
public class DataBean {
    
    private static final DataService service = new DataService();
    private int month;
    private int year;
    private long income;
    /**
     * Creates a new instance of DataBean
     */
    public DataBean() {
        this.year = 2020;
    }
    
    public String calData() {
        Map<String, Object> rs = service.calculateData(this.month, this.year);
        this.income = (long)rs.get("income");
        return "index?faces-redirect=true";
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the income
     */
    public long getIncome() {
        return income;
    }

    /**
     * @param income the income to set
     */
    public void setIncome(long income) {
        this.income = income;
    }
    
}
