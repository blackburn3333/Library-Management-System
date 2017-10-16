/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import LMS_DBConnection.LMS_DatabaseConnection;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import static LMS_business.LMS_MessageBoxes.ShowMessage;
import com.jfoenix.controls.JFXButton;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
/**
 *
 * @author Jayen
 */
public class LMS_BookInfo {
    readwithuslms.LMS_bookManageController bookM = new readwithuslms.LMS_bookManageController();
    
    public static Connection con = LMS_DatabaseConnection.DBConnection();
    public static java.sql.PreparedStatement pst = null;
    public static ResultSet rs = null; 
    
    public static void LMS_viewBook(TableView<LMS_BookDetails> table, String book_Count) {
        ObservableList<LMS_BookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT * FROM `book` WHERE `LMS_bookCount` >= '"+book_Count+"'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()){
                String LMS_booklId = rs.getString("LMS_booklId");
                String LMS_bookName = rs.getString("LMS_bookName");
                String LMS_bookAuthor = rs.getString("LMS_bookAuthor");
                String LMS_bookCategory = rs.getString("LMS_bookCategory");
                int LMS_bookCount = rs.getInt("LMS_bookCount");
                String LMS_bookIsbn =  rs.getString("LMS_bookIsbn");
                list.add(new LMS_BookDetails(LMS_bookName, LMS_booklId, LMS_bookIsbn, LMS_bookAuthor, LMS_bookCategory, LMS_bookCount));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }
    
    public static void LMS_BookSearch(TableView<LMS_BookDetails> table, JFXTextField SearchVal, String bookCount){
        ObservableList<LMS_BookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT * FROM `book` WHERE `LMS_bookCount` >= '"+bookCount+"' and CONCAT(`LMS_bookName`,`LMS_bookAuthor`,`LMS_bookIsbn`,`LMS_booklId`,`LMS_bookCount`,`LMS_bookCategory`) LIKE '%" +SearchVal.getText()+ "%'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()){
                String LMS_booklId = rs.getString("LMS_booklId");
                String LMS_bookName = rs.getString("LMS_bookName");
                String LMS_bookAuthor = rs.getString("LMS_bookAuthor");
                String LMS_bookCategory = rs.getString("LMS_bookCategory");
                int LMS_bookCount = rs.getInt("LMS_bookCount");
                String LMS_bookIsbn =  rs.getString("LMS_bookIsbn");
                list.add(new LMS_BookDetails(LMS_bookName, LMS_booklId, LMS_bookIsbn, LMS_bookAuthor, LMS_bookCategory, LMS_bookCount));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }
    
    public static void LMS_bookAdd(JFXTextField ID, JFXTextField title, JFXTextField author, ComboBox category, JFXTextField quantity,JFXTextField isbn) {
        try {
            
            String CheckDupicateID = "SELECT COUNT(*) FROM `book` WHERE `LMS_booklId` = '"+"RWU"+ID.getText() +"'";
            pst = (PreparedStatement) con.prepareStatement(CheckDupicateID);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                int duplicateBook = rs.getInt("COUNT(*)");
                if(duplicateBook >= 1)
                {
                    ShowMessage(Alert.AlertType.INFORMATION, "Duplicate Book ID !!");
                }else
                {
                    if (!title.getText().isEmpty() && !author.getText().isEmpty() && !quantity.getText().isEmpty() && !ID.getText().isEmpty() && !isbn.getText().isEmpty()) {
                if (!category.getSelectionModel().isEmpty()) {
                    String cmd = "INSERT INTO `book`(`LMS_bookName`, `LMS_bookAuthor`, `LMS_bookIsbn`, `LMS_booklId`, `LMS_bookCount`, `LMS_bookCategory`) VALUES ('" + title.getText() + "','" + author.getText() + "','" + "ISBN"+isbn.getText() + "','" +"RWU"+ID.getText() + "','" + quantity.getText() + "','"+category.getValue().toString()+"' )";
                    pst = (PreparedStatement) con.prepareStatement(cmd);
                    int result = pst.executeUpdate();
                    if (result > 0) {
                        System.out.println("Value inserted successfully!");
                        ShowMessage(Alert.AlertType.INFORMATION, "Value inserted successfully!");
                        ID.clear();
                        title.clear();
                        author.clear();
                        category.getSelectionModel().select(-1);
                        isbn.clear();
                        quantity.clear();
                    } else {
                        System.out.println("Error occured!");
                        ShowMessage(Alert.AlertType.ERROR, "Error occurred!");
                    }
                } else {

                    ShowMessage(Alert.AlertType.ERROR, "Please select book category!");
                }

            } else {

                ShowMessage(Alert.AlertType.ERROR, "Please fill out empty fields!");

            }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void updateBookinfo(JFXTextField SearchVal,JFXTextField ID, JFXTextField title, JFXTextField author, ComboBox category, JFXTextField quantity,JFXTextField isbn){
        System.err.println(SearchVal.getText());
        System.err.println(ID.getText());
        System.err.println(title.getText());
        System.err.println(author.getText());
        System.err.println(category.getValue().toString());
        System.err.println(quantity.getText());
        System.err.println(isbn.getText());
        try {
            if (!ID.getText().isEmpty() && !title.getText().isEmpty() && !author.getText().isEmpty() && !quantity.getText().isEmpty()) {
                String cmd = "UPDATE `book` SET `LMS_bookName`='" + title.getText() + "',`LMS_bookAuthor`='" + author.getText() + "',`LMS_bookIsbn`='" + isbn.getText() + "',`LMS_booklId`='" + ID.getText() + "',`LMS_bookCount`='" + quantity.getText() + "',`LMS_bookCategory`='" + category.getValue().toString()+ "' WHERE `LMS_booklId` = '" + SearchVal.getText() + "'";
                pst = (PreparedStatement) con.prepareStatement(cmd);
                int result = pst.executeUpdate();
                if (result > 0) {
                    System.out.println("Book updated successfully!");
                    ShowMessage(Alert.AlertType.INFORMATION, "Book updated successfully!");
                    SearchVal.clear();
                    ID.clear();
                    title.clear();
                    author.clear();
                    quantity.clear();
                    isbn.clear();
                    category.setPromptText("Select Book Category");
                } else {
                    System.out.println("Error occured!");
                    ShowMessage(Alert.AlertType.ERROR, "Error occurred!");
                }
            } else {
                ShowMessage(Alert.AlertType.ERROR, "Please fill out empty fields!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void SearchBook(JFXTextField SearchVal, JFXTextField ID, JFXTextField title, JFXTextField author, ComboBox category, JFXTextField quantity,JFXTextField isbn){
        try{
            if(SearchVal.getText().isEmpty()){
                ShowMessage(Alert.AlertType.INFORMATION, "Please Insert Book ID");
            }else{
                
                String cmd = "SELECT * FROM `book` WHERE `LMS_booklId` = '"+SearchVal.getText()+"'";
                pst = (PreparedStatement) con.prepareStatement(cmd);
                rs = pst.executeQuery();
               
                if(rs.next()){
                    title.setPromptText(rs.getString("LMS_bookName"));
                    author.setPromptText(rs.getString("LMS_bookAuthor"));
                    isbn.setPromptText(rs.getString("LMS_bookIsbn"));
                    ID.setPromptText(rs.getString("LMS_booklId"));
                    quantity.setPromptText(rs.getString("LMS_bookCount"));
                    category.setPromptText(rs.getString("LMS_bookCategory"));
                    
                    title.setText(rs.getString("LMS_bookName"));
                    author.setText(rs.getString("LMS_bookAuthor"));
                    isbn.setText(rs.getString("LMS_bookIsbn"));
                    ID.setText(rs.getString("LMS_booklId"));
                    quantity.setText(rs.getString("LMS_bookCount"));
                    category.setValue(rs.getString("LMS_bookCategory"));
                    
                }else{
                ShowMessage(Alert.AlertType.WARNING, "Invalid ID");
                }
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void deleteBook(JFXTextField SearchVal){
        try {
            if (!SearchVal.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText(null);
                alert.setContentText("Are you want to delete this record?");
                Optional<ButtonType> confirm = alert.showAndWait();
                if (confirm.isPresent() && confirm.get() == ButtonType.YES) {
                    String cmd = "DELETE FROM `book` WHERE `LMS_booklId` ='" + SearchVal.getText() + "'";
                    pst = (PreparedStatement) con.prepareStatement(cmd);
                    int result = pst.executeUpdate();
                    if (result > 0) {
                        ShowMessage(Alert.AlertType.INFORMATION, "Successfully deleted!");
                    } else {
                        ShowMessage(Alert.AlertType.ERROR, "Error occurred!");
                    }
                }
            } else {
                ShowMessage(Alert.AlertType.ERROR, "Please select book to delete!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void refrestTable(){
        
    }
    
    public static void LMS_viewBookChechbook(Text Avilable, JFXTextField bookID, JFXButton issu,Text bname,Text bisbn,Text author) {
        if(!bookID.getText().isEmpty())
        {
            try {
            String cmd = "SELECT * FROM `book` WHERE `LMS_booklId` = '"+bookID.getText()+"' AND `LMS_bookCount` >= 1";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            if (rs.next()){
                //String id = rs.getString("LMS_booklId");
                //System.out.print(id);
                bname.setText(rs.getString("LMS_bookName"));
                bisbn.setText(rs.getString("LMS_bookIsbn"));
                author.setText(rs.getString("LMS_bookAuthor"));
                Avilable.setText("Available");
                Avilable.setFill(Color.GREEN);
                issu.setDisable(false);
            }else{
                Avilable.setText("Not Available");
                Avilable.setFill(Color.RED);
                issu.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }
        else
        {
            ShowMessage(Alert.AlertType.ERROR, "Please Enter Book ID!");
        }
        
    }
    
    public static void LMS_viewBookChechbookRes(Text Avilable, JFXTextField bookID,Text bname,Text bisbn) {
        if(!bookID.getText().isEmpty())
        {
            try {
            String cmd = "SELECT * FROM `book` WHERE `LMS_booklId` = '"+bookID.getText()+"' AND `LMS_bookCount` >= 1";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            if (rs.next()){
                //String id = rs.getString("LMS_booklId");
                //System.out.print(id);
                bname.setText(rs.getString("LMS_bookName"));
                bisbn.setText(rs.getString("LMS_bookIsbn"));
                //author.setText(rs.getString("LMS_bookAuthor"));
                Avilable.setText("Available");
                Avilable.setFill(Color.GREEN);
                //ress.setDisable(false);
            }else{
                Avilable.setText("Not Available");
                Avilable.setFill(Color.RED);
                //ress.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }
        else
        {
            ShowMessage(Alert.AlertType.ERROR, "Please Enter Book ID!");
        }
        
    }
    
    public static void LMS_bookIssu(String MemID,JFXTextField BookID,Text BarrowDate,Text BookReturnDate,JFXTextField AdminID){
        try
        {
            String cmd = "SELECT COUNT(*) FROM `lms_bookbarrow` WHERE `lma_memberID` = '"+MemID+"'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            if(rs.next())
            {
                int Count = rs.getInt("COUNT(*)");
                System.err.println(Count);
                if(Count >=2)
                {
                   System.err.println("you cant");
                }
                else
                {
                     System.err.println("you can");
                }
            }else{
                System.err.println("Valid Member ID");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
