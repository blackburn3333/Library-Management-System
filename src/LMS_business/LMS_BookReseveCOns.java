/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jayen
 */
public class LMS_BookReseveCOns {
    private final StringProperty Res_bookID;
    private final StringProperty Res_bookName;
    private final StringProperty Res_MemberID;
    private final StringProperty Res_MemberName;
    
    public LMS_BookReseveCOns(String Res_bookID, String Res_bookName, String Res_MemberID, String Res_MemberName) {
        
        this.Res_bookID = new SimpleStringProperty(Res_bookID);
        this.Res_bookName = new SimpleStringProperty(Res_bookName);
        this.Res_MemberID = new SimpleStringProperty(Res_MemberID);
        this.Res_MemberName = new SimpleStringProperty(Res_MemberName);
        
    }

    //getters
    public String getRes_bookID() {
        return Res_bookID.get();
    }

    public String getRes_bookName() {
        return Res_bookName.get();
    }

    public String getRes_MemberID() {
        return Res_MemberID.get();
    }

    public String getRes_MemberName() {
        return Res_MemberName.get();
    }
    
    //setters
    public void setRes_bookID(String value) {
        Res_bookID.set(value);
    }
    
    public void setRes_bookName(String value) {
        Res_bookName.set(value);
    }
    public void setRes_MemberID(String value) {
        Res_MemberID.set(value);
    }
    public void setRes_MemberName(String value) {
        Res_MemberName.set(value);
    }
    
    
}
