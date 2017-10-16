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
import javafx.scene.text.Text;

/**
 *
 * @author Jayen
 */
public class LMS_RowCounter {
    
    static Connection con = null;
    static PreparedStatement pst = null;
    static ResultSet rs = null;
    
    public static void LMS_Counter(String TableName, Text BookCount){
        try
       {
            con = LMS_DatabaseConnection.DBConnection();
            String Bcount = "SELECT COUNT(*) FROM "+TableName+"";
            pst = con.prepareStatement(Bcount);
            rs = pst.executeQuery();
            if(rs.next())
            {
                String Count = rs.getString("COUNT(*)");
                System.out.println(Count);
                BookCount.setText(Count);
            }
       }
       catch(Exception ex)
       {
        ex.printStackTrace();
       }
    }
    
    public static void LMS_AvilbleBookCounter(String TableName, Text BookCount){
        try
       {
            con = LMS_DatabaseConnection.DBConnection();
            String Bcount = "SELECT COUNT(*) FROM "+TableName+" WHERE `LMS_bookCount` >= 1";
            pst = con.prepareStatement(Bcount);
            rs = pst.executeQuery();
            if(rs.next())
            {
                String Count = rs.getString("COUNT(*)");
                System.out.println(Count);
                BookCount.setText(Count);
            }
       }
       catch(Exception ex)
       {
        ex.printStackTrace();
       }
    }
    
    public static void LMS_AvilbleCopisCounter(String RowName, String TableName, Text BookCount){
        try
       {
            con = LMS_DatabaseConnection.DBConnection();
            String Bcount = "SELECT SUM("+RowName+") FROM "+TableName+" WHERE `LMS_bookCount` >= 1";
            pst = con.prepareStatement(Bcount);
            rs = pst.executeQuery();
            if(rs.next())
            {
                String Count = rs.getString("SUM("+RowName+")");
                System.out.println(Count);
                BookCount.setText(Count);
            }
       }
       catch(Exception ex)
       {
        ex.printStackTrace();
       }
    }
    
    public static void LMS_SumCounter(String RowName,String TableName, Text BookSumCount){
        try
       {
            con = LMS_DatabaseConnection.DBConnection();
            String Bcount = "SELECT SUM("+RowName+") FROM "+TableName+"";
            pst = con.prepareStatement(Bcount);
            rs = pst.executeQuery();
            if(rs.next())
            {
                String CoutS = rs.getString("SUM("+RowName+")");
                if(CoutS == "0" || CoutS == null)
                {
                    BookSumCount.setText("0");
                }else
                {
                    String Count = rs.getString("SUM("+RowName+")");
                System.out.println(Count);
                BookSumCount.setText(Count);
                }
                
                
            }
       }
       catch(Exception ex)
       {
        ex.printStackTrace();
       }
    }
    
    public static void LMS_MemTypeCounter(String TableName, Text MemerCount,String MemerType){
        try
       {
            con = LMS_DatabaseConnection.DBConnection();
            String Bcount = "SELECT COUNT(`user_type`) FROM "+TableName+" WHERE `user_type` = "+MemerType+"";
            pst = con.prepareStatement(Bcount);
            rs = pst.executeQuery();
            if(rs.next())
            {
                String Count = rs.getString("COUNT(`user_type`)");
                System.out.println(Count);
                MemerCount.setText(Count);
                String Nousers = "0";
                if(Count.equals(Nousers)){
                    MemerCount.setText("No Any Users");
                }
            }else{
                
            }
       }
       catch(Exception ex)
       {
        ex.printStackTrace();
       }
    }
    
    public static void LMS_BarrowBcount(String userID, Text BarrowBookCount, Text Fine){
        try
        {
          con = LMS_DatabaseConnection.DBConnection();
          String BBarrowcount = "SELECT COUNT(*),SUM(`bookBarrow_Fine`) FROM `lms_bookbarrow` WHERE `lma_memberID` = '"+userID+"'";
          pst = con.prepareStatement(BBarrowcount);
          rs = pst.executeQuery();
            if(rs.next())
            {
                BarrowBookCount.setText(rs.getString("COUNT(*)"));
                Fine.setText(rs.getString("SUM(`bookBarrow_Fine`)"));
            }else{
                
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
