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
public class LMS_MemberDetails {
    private final StringProperty MName;
    private final StringProperty MNic;
    private final StringProperty MAddress;
    private final StringProperty MCont;
    private final StringProperty MType;
    

    public LMS_MemberDetails(String MName, String MNic, String MAddress, String MCont,String MType) {
        this.MName =  new SimpleStringProperty(MName);
        this.MNic = new SimpleStringProperty(MNic);
        this.MAddress = new SimpleStringProperty(MAddress);
        this.MCont = new SimpleStringProperty(MCont);
        this.MType = new SimpleStringProperty(MType);
    }

    public String getMType() {
        return MType.get();
    }
    
    public String getMName() {
        return MName.get();
    }

    public String getMNic() {
        return MNic.get();
    }

    public String getMAddress() {
        return MAddress.get();
    }

    public String getMCont() {
        return MCont.get();
    }
    
    
    public void  setMName(String value) {
        MName.set(value);
    }

    public void setMNic(String value) {
        MNic.set(value);
    }

    public void setMAddress(String value) {
        MAddress.set(value);
    }

    public void setMCont(String value) {
        MCont.set(value);
    }
    
   public void setMType(String value) {
        MType.set(value);
    }
}
