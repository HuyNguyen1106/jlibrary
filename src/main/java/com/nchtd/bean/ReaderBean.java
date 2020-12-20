/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.Reader;
import com.nchtd.services.ReaderService;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;

/**
 *
 * @author dinh3
 */
@Named(value = "readerBean")
@RequestScoped
@ManagedBean
public class ReaderBean {
    private static final ReaderService readerService = new ReaderService();
    private Integer readId;
    private String first;
    private String last;
    private String email;
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    private String phone;
    private String address;
    
    /**
     * Creates a new instance of ReaderBean
     */
    public ReaderBean() {
        if (!FacesContext.getCurrentInstance().isPostback()){
            String readerId = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("reader_id");
            if (readerId != null && !readerId.isEmpty()){    
                Reader reader = readerService.getReaderById(Integer.parseInt(readerId));
                this.readId = reader.getId();
                this.first = reader.getFirstname();
                this.last = reader.getLastname();
                this.email = reader.getEmail();
                this.phone = reader.getPhone();
                this.address = reader.getAddress();
            }
        }
    }
    
    public List<Reader> getList() {
        return readerService.getAll();
    }
    
    public String addReader() {
//        String readerId = FacesContext
//                .getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap()
//                .get("reader_id");
        Reader c;
        if (this.readId != null){
            c = readerService.getReaderById(this.readId);
        } else {
            c = new Reader(); 
        }         
        c.setFirstname(this.first);
        c.setLastname(this.last);
        c.setEmail(this.email);
        c.setPhone(this.phone);
        c.setAddress(this.address);
        
        if(readerService.addOrSave(c) == true) {
            return "index?faces-redirect=true";
        }
        
        return "create";
    }
    
    public String deleteReader (Reader reader) throws Exception{
        reader.setDeletedAt(new Date());
        if (readerService.addOrSave(reader) == true){
            return "successful";
        }
        throw new Exception("Delete failed");
    }
    
    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the readId
     */
    public Integer getReadId() {
        return readId;
    }

    /**
     * @param readId the readId to set
     */
    public void setReadId(Integer readId) {
        this.readId = readId;
    }
    
}
