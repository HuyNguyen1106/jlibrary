/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.POJO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Embeddable
public class PaymentDetailPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_id")
    private int paymentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_id")
    private int bookId;

    public PaymentDetailPK() {
    }

    public PaymentDetailPK(int paymentId, int bookId) {
        this.paymentId = paymentId;
        this.bookId = bookId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) paymentId;
        hash += (int) bookId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentDetailPK)) {
            return false;
        }
        PaymentDetailPK other = (PaymentDetailPK) object;
        if (this.paymentId != other.paymentId) {
            return false;
        }
        if (this.bookId != other.bookId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nchtd.POJO.PaymentDetailPK[ paymentId=" + paymentId + ", bookId=" + bookId + " ]";
    }
    
}
