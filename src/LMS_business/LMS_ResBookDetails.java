/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

/**
 *
 * @author Jayen
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LMS_ResBookDetails {
    
    private final StringProperty id;
    private final StringProperty bName;
    private final StringProperty author;
    private final StringProperty ResEndDate;

    LMS_ResBookDetails(String LMS_booklId, String LMS_bookName, String LMS_bookAuthor, String LMS_ResDate) {
        this.id = new SimpleStringProperty(LMS_booklId);
        this.bName = new SimpleStringProperty(LMS_bookName);
        this.author = new SimpleStringProperty(LMS_bookAuthor);
        this.ResEndDate = new SimpleStringProperty(LMS_ResDate);
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

    public String getResEndDate() {
        return ResEndDate.get();
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
    
    public void setResEndDate(String value) {
        ResEndDate.set(value);
    }
}
