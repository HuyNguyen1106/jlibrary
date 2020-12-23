/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.POJO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "payment_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentDetail.findAll", query = "SELECT p FROM PaymentDetail p"),
    @NamedQuery(name = "PaymentDetail.findByPaymentId", query = "SELECT p FROM PaymentDetail p WHERE p.paymentDetailPK.paymentId = :paymentId"),
    @NamedQuery(name = "PaymentDetail.findByBookId", query = "SELECT p FROM PaymentDetail p WHERE p.paymentDetailPK.bookId = :bookId"),
    @NamedQuery(name = "PaymentDetail.findByUnitPrice", query = "SELECT p FROM PaymentDetail p WHERE p.unitPrice = :unitPrice"),
    @NamedQuery(name = "PaymentDetail.findByQuantiry", query = "SELECT p FROM PaymentDetail p WHERE p.quantiry = :quantiry")})
public class PaymentDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PaymentDetailPK paymentDetailPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unit_price")
    private long unitPrice;
    @Basic(optional = false)
    @NotNull
    private int quantiry;
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Book book;
    @JoinColumn(name = "payment_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Payment payment;

    public PaymentDetail() {
    }

    public PaymentDetail(PaymentDetailPK paymentDetailPK) {
        this.paymentDetailPK = paymentDetailPK;
    }

    public PaymentDetail(PaymentDetailPK paymentDetailPK, long unitPrice, int quantiry) {
        this.paymentDetailPK = paymentDetailPK;
        this.unitPrice = unitPrice;
        this.quantiry = quantiry;
    }

    public PaymentDetail(int paymentId, int bookId) {
        this.paymentDetailPK = new PaymentDetailPK(paymentId, bookId);
    }

    public PaymentDetailPK getPaymentDetailPK() {
        return paymentDetailPK;
    }

    public void setPaymentDetailPK(PaymentDetailPK paymentDetailPK) {
        this.paymentDetailPK = paymentDetailPK;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantiry() {
        return quantiry;
    }

    public void setQuantiry(int quantiry) {
        this.quantiry = quantiry;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentDetailPK != null ? paymentDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentDetail)) {
            return false;
        }
        PaymentDetail other = (PaymentDetail) object;
        if ((this.paymentDetailPK == null && other.paymentDetailPK != null) || (this.paymentDetailPK != null && !this.paymentDetailPK.equals(other.paymentDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nchtd.POJO.PaymentDetail[ paymentDetailPK=" + paymentDetailPK + " ]";
    }
    
}
