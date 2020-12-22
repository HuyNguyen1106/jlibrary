/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nchtd.bean;

import com.nchtd.POJO.Book;
import com.nchtd.POJO.Category;
import com.nchtd.services.BookService;
import com.sun.istack.logging.Logger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@Named(value = "bookBean")
@RequestScoped
@ManagedBean
public class BookBean {
    private static final BookService bookService = new BookService();
    private String title;
    private String description;
    private String author;
    private long unitPrice;
    private Category categoryId;
    private Integer releaseYear;
    private Part imageFile;
    
    
    /**
     * Creates a new instance of BookBean
     */
    public BookBean() {
    }
    
    public String addBook() throws IOException {
        Book b = new Book();
        b.setActive(Short.parseShort("1"));
        b.setTitle(this.title);        
        b.setDescription(this.description);
        b.setAuthor(this.author);
        b.setUnitPrice(this.unitPrice);
        b.setCategoryId(this.categoryId);
        b.setReleaseYear(this.releaseYear);
        Date date = new Date();
        b.setCreatedAt(date);
        b.setUpdatedAt(date);
        b.setAvailableCount(0);
        b.setTotalCount(0);
        try {
            this.uploadFile();
            b.setImage("upload/"+this.imageFile.getSubmittedFileName());
            if(bookService.addOrSave(b) == true) {
                return "index?faces-redirect=true";
            }
        } catch (IOException e) {
            Logger.getLogger(BookBean.class).log(Level.SEVERE, e.getMessage());
        }
        
        
        return "create";
    }
    
    private void uploadFile() throws IOException {
        String path = FacesContext.getCurrentInstance()
                .getExternalContext().getInitParameter("uploadPath") + this.imageFile.getSubmittedFileName();
        try (InputStream in = this.imageFile.getInputStream();
                FileOutputStream out = new FileOutputStream(path)) {
            int byteRead;
            byte[] chunk = new byte[1024];
            while ((byteRead = in.read(chunk)) != -1) {
                out.write(chunk, 0, byteRead);
            }
        }
    }
    
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the unitPrice
     */
    public long getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the categoryId
     */
    public Category getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the releaseYear
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * @param releaseYear the releaseYear to set
     */
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * @return the imageFile
     */
    public Part getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFile the imageFile to set
     */
    public void setImageFile(Part imageFile) {
        this.imageFile = imageFile;
    }
}
