/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import LMS_DBConnection.LMS_DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jayen
 */
public class LMS_FineCalcuator {
    public static Connection con = LMS_DatabaseConnection.DBConnection();
    static PreparedStatement pst = null;
    static ResultSet rs = null;
    
    
    
    public static void lms_faineCal(){
        try
        {
        String cmdFcount = "SELECT *,(cast(current_timestamp as date) - cast(`bookBarrowEndDate` as date) ) * 5 as fine FROM `lms_bookbarrow` WHERE `bookBarrowEndDate` <= current_timestamp";
        pst = con.prepareStatement(cmdFcount);
        rs = pst.executeQuery();
        while(rs.next()){
             String BookIssuID = rs.getString("book_issuID");
             String Fine = rs.getString("fine");
             if(Fine.equals("0")){
                 //System.out.println("No fine");
                 String FineAdd = "UPDATE `lms_bookbarrow` SET `bookBarrow_Fine`= '0'  WHERE `book_issuID` = "+BookIssuID+"";
                 pst = con.prepareStatement(FineAdd);
                 pst.executeUpdate();
             }else
             {
                 String FineAdd = "UPDATE `lms_bookbarrow` SET `bookBarrow_Fine`= "+Fine+"  WHERE `book_issuID` = "+BookIssuID+"";
                 pst = con.prepareStatement(FineAdd);
                 pst.executeUpdate();
             }
        }
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }
    
    }
    
    public static void lms_removeResbooks(){
    try
    {
        String GetResEndBooks = "SELECT * FROM `lms_bookreseve` WHERE `lms_bookReserveEndDate` <= current_timestamp";
        pst = con.prepareStatement(GetResEndBooks);
        rs = pst.executeQuery();
        while(rs.next()){
            String ResID = rs.getString("lms_bookReserveID");
            String DeleteRes = "DELETE FROM `lms_bookreseve` WHERE `lms_bookReserveID` = '"+ResID+"'";
            pst = con.prepareStatement(DeleteRes);
            pst.execute();
            
            String BookID = rs.getString("lms_bookResBookID");
            String UpdQuan = "UPDATE `book` SET `LMS_bookCount` = `LMS_bookCount`+1 WHERE `LMS_booklId` = '"+BookID+"'";
            pst = con.prepareStatement(UpdQuan);
            pst.execute();
        }
    }
    catch(Exception ex)
    {
        System.err.println(ex);
    }
    }
}
