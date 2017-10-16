/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;
import LMS_DBConnection.LMS_DatabaseConnection;
import static LMS_business.LMS_BookBarrowandReturn.con;
import static LMS_business.LMS_BookBarrowandReturn.pst;
import static LMS_business.LMS_BookBarrowandReturn.rs;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jfoenix.controls.JFXTextField;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
/**
 *
 * @author Jayen
 */
public class LMS_bookOverduebookFine {
    public static Connection con = LMS_DatabaseConnection.DBConnection();
    static PreparedStatement pst = null;
    static ResultSet rs = null;
    
    public static void LMS_fineCalcu(String UserName,String TodayDate){
        try{
        System.out.print("-----------------------------");
        System.out.println(UserName);
        System.out.println(TodayDate);
        String cmdBcount = "SELECT * FROM `lms_bookbarrow` WHERE `lma_memberID` = '"+UserName+"' and `bookBarrowEndDate` <= '"+TodayDate+"'";
        pst = con.prepareStatement(cmdBcount);
        rs = pst.executeQuery();
        while(rs.next()){
            System.out.println("-----------------------------");
            System.out.println("-----Fine Detils-----");
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
            System.out.println("MemberID:" + rs.getString("lma_memberID"));
            System.out.println("EndDate:" +rs.getString("bookBarrowEndDate"));
            System.out.println("Today Date:" + TodayDate);
            
            Date BorrowDate = rs.getDate("bookBarrowEndDate");
            Date ToDate  =  new SimpleDateFormat("MM-dd-yyyy").parse(TodayDate);
            
            Date OverDue = new Date (ToDate.getTime() - BorrowDate.getTime());
            
            System.out.println("OverDue Date:" + OverDue);
            System.out.println("-----------------------------");
        }
        }catch(Exception ex){
            System.err.println(ex);
            System.out.print("-----------------------------");
        }
    }
    
}
