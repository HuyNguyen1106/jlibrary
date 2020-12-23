/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findByDescription", query = "SELECT b FROM Book b WHERE b.description = :description"),
    @NamedQuery(name = "Book.findByUnitPrice", query = "SELECT b FROM Book b WHERE b.unitPrice = :unitPrice"),
    @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author"),
    @NamedQuery(name = "Book.findByReleaseYear", query = "SELECT b FROM Book b WHERE b.releaseYear = :releaseYear"),
    @NamedQuery(name = "Book.findByCreatedAt", query = "SELECT b FROM Book b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "Book.findByUpdatedAt", query = "SELECT b FROM Book b WHERE b.updatedAt = :updatedAt"),
    @NamedQuery(name = "Book.findByActive", query = "SELECT b FROM Book b WHERE b.active = :active"),
    @NamedQuery(name = "Book.findByAvailableCount", query = "SELECT b FROM Book b WHERE b.availableCount = :availableCount"),
    @NamedQuery(name = "Book.findByTotalCount", query = "SELECT b FROM Book b WHERE b.totalCount = :totalCount"),
    @NamedQuery(name = "Book.findByImage", query = "SELECT b FROM Book b WHERE b.image = :image")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unit_price")
    private long unitPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    private String author;
    @Column(name = "release_year")
    private Integer releaseYear;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    private short active;
    @Column(name = "available_count")
    private Integer availableCount;
    @Column(name = "total_count")
    private Integer totalCount;
    @Size(max = 255)
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.EAGER)
    private List<PaymentDetail> paymentDetailList;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category categoryId;

    public Book() {
    }

    public Book(Integer id) {
        this.id = id;
    }

    public Book(Integer id, String title, String description, long unitPrice, String author, short active) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.unitPrice = unitPrice;
        this.author = author;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
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

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public List<PaymentDetail> getPaymentDetailList() {
        return paymentDetailList;
    }

    public void setPaymentDetailList(List<PaymentDetail> paymentDetailList) {
        this.paymentDetailList = paymentDetailList;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nchtd.POJO.Book[ id=" + id + " ]";
    }
    
}
