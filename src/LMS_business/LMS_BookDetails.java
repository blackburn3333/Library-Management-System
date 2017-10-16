/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jayen
 */
public class LMS_BookDetails {
    private final StringProperty bName;
    private final StringProperty id;
    private final StringProperty BookIsbn;
    private final StringProperty author;
    private final StringProperty category;
    private final IntegerProperty LMS_bookCount;
    
    public LMS_BookDetails(String bName, String id, String BookIsbn, String author, String category, Integer LMS_ColbookCount) {
        
        this.bName = new SimpleStringProperty(bName);
        this.id = new SimpleStringProperty(id);
        this.BookIsbn = new SimpleStringProperty(BookIsbn);
        this.author = new SimpleStringProperty(author);
        this.category = new SimpleStringProperty(category);
        this.LMS_bookCount = new SimpleIntegerProperty(LMS_ColbookCount);
        
    }
    
   //Getters
     public String getBName() {
        return bName.get();
    }
     
   public String getId() {
        return id.get();
    }
    
   
    
    public String getAuthor() {
        return author.get();
    }
    
    public String getCategory() {
        return category.get();
    }
    
    public Integer getLMS_bookCount() {
        return LMS_bookCount.get();
    }

    public String getBookIsbn() {
        return BookIsbn.get();
    }
    
    //Setters

    public void setId(String value) {
        id.set(value);
    }

    public void setBName(String value) {
        bName.set(value);
    }

    public void setAuthor(String value) {
        author.set(value);
    }

    public void setCategory(String value) {
        category.set(value);
    }

    public void setLMS_bookCount(Integer value) {
        LMS_bookCount.set(value);
    }
    
    public void setBookIsbn(String value) {
        BookIsbn.set(value);
    }
}
