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
public class LMS_BorrowBookDetails {
    private final StringProperty borrowBookID;
    private final StringProperty borrowBookName;
    private final StringProperty borrowEndDate;
    private final StringProperty borrowFine;
    private final StringProperty borrowMemberID;
    
    public LMS_BorrowBookDetails(String borrowBookID,String borrowBookName,String borrowEndDate,String borrowFine,String borrowMemberID)
    {
        this.borrowBookID =  new SimpleStringProperty(borrowBookID);
        this.borrowBookName =  new SimpleStringProperty(borrowBookName);
        this.borrowEndDate =  new SimpleStringProperty(borrowEndDate);
        this.borrowFine =  new SimpleStringProperty(borrowFine);
        this.borrowMemberID = new SimpleStringProperty(borrowMemberID);
    }

    public String getBorrowMemberID() {
        return borrowMemberID.get();
    }
    
    
    public String getBorrowBookID() {
        return borrowBookID.get();
    }

    public String getBorrowBookName() {
        return borrowBookName.get();
    }

    public String getBorrowEndDate() {
        return borrowEndDate.get();
    }

    public String getBorrowFine() {
        return borrowFine.get();
    }
    
    public void  setBorrowMemberID(String value) {
        borrowMemberID.set(value);
    }
    
    public void  setBorrowBookID(String value) {
        borrowBookID.set(value);
    }
    
    public void  setBorrowBookName(String value) {
        borrowBookName.set(value);
    }
    
    public void  setBorrowEndDat(String value) {
        borrowEndDate.set(value);
    }
    
    public void  setBorrowFine(String value) {
        borrowFine.set(value);
    }
}
