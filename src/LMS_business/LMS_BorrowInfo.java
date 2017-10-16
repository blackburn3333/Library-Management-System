/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jayen
 */
public class LMS_BorrowInfo {
    private final StringProperty IssuID;
    private final StringProperty MemberName;
    private final StringProperty Membercon;
    
    public LMS_BorrowInfo(String IssuID,String MemberName,String Membercon)
    {
        this.IssuID =  new SimpleStringProperty(IssuID);
        this.MemberName =  new SimpleStringProperty(MemberName);
        this.Membercon =  new SimpleStringProperty(Membercon);
    }

    public String getIssuID() {
        return IssuID.get();
    }

    public String getMemberName() {
        return MemberName.get();
    }

    public String getMembercon() {
        return Membercon.get();
    }

    //Setters
    
    public void  setMemberName(String value) {
        MemberName.set(value);
    }
    public void  setMembercon(String value) {
        Membercon.set(value);
    }
    public void  setIssuID(String value) {
        IssuID.set(value);
    }
    
}
