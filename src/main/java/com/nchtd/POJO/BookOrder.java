/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.POJO;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookOrder.findAll", query = "SELECT b FROM BookOrder b"),
    @NamedQuery(name = "BookOrder.findById", query = "SELECT b FROM BookOrder b WHERE b.id = :id"),
    @NamedQuery(name = "BookOrder.findByReturnDate", query = "SELECT b FROM BookOrder b WHERE b.returnDate = :returnDate"),
    @NamedQuery(name = "BookOrder.findByExtraFee", query = "SELECT b FROM BookOrder b WHERE b.extraFee = :extraFee"),
    @NamedQuery(name = "BookOrder.findByReader", query = "SELECT b FROM BookOrder b WHERE b.reader = :reader"),
    @NamedQuery(name = "BookOrder.findByCreatedAt", query = "SELECT b FROM BookOrder b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "BookOrder.findByUpdatedAt", query = "SELECT b FROM BookOrder b WHERE b.updatedAt = :updatedAt"),
    @NamedQuery(name = "BookOrder.findByDeletedAt", query = "SELECT b FROM BookOrder b WHERE b.deletedAt = :deletedAt")})
public class BookOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Column(name = "extra_fee")
    private BigInteger extraFee;
    
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
    
    @OneToMany(mappedBy = "orderId")
    private Collection<OrderDetail> orderDetailCollection;
    
    @JoinColumn(name = "reader_id", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
    private Reader reader;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private User userId;

    public BookOrder() {
    }

    public BookOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BigInteger getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(BigInteger extraFee) {
        this.extraFee = extraFee;
    }

    

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookOrder)) {
            return false;
        }
        BookOrder other = (BookOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
    
}
