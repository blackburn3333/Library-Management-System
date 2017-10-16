/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import LMS_DBConnection.LMS_DatabaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jfoenix.controls.JFXTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


/**
 *
 * @author Jayen
 */
public class LMS_Users {
    public static Connection con = LMS_DatabaseConnection.DBConnection();
    public static java.sql.PreparedStatement pst = null;
    public static ResultSet rs = null;
    public static String userN=null;
    
    public static String UserType = null;
    
    public static void LMS_UserInfo(String UN,Text Unm){
        try
        {
        String cmd = "SELECT * FROM `lms_users` WHERE `user_name` = '"+UN+"'";
           pst = con.prepareStatement(cmd);
           rs = pst.executeQuery();
           if(rs.next()){
                Unm.setText(rs.getString("LMS_UserFname"));
                System.err.println(rs.getString("LMS_UserFname"));
        }
        }catch(Exception ex)
        {
            System.err.println(ex);
        }
        
    }
    
}
