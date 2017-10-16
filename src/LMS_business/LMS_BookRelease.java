/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import LMS_DBConnection.LMS_DatabaseConnection;
import static LMS_business.LMS_MessageBoxes.ShowMessage;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

/**
 *
 * @author Jayen
 */
public class LMS_BookRelease {

    public static Connection con = LMS_DatabaseConnection.DBConnection();
    public static java.sql.PreparedStatement pst = null;
    public static ResultSet rs = null;
    LMS_business.LMS_Windows LMS_Windows = new LMS_business.LMS_Windows();
    
    public static void LMS_GetReleaseData(String IssuID, Text BookID, Text MemberName, Text BookName, Text IssuSetID, Text IssuFIne) {
        System.out.print(IssuID);
        try {
            String getBorrowDate = "SELECT lms_bookbarrow.book_issuID,lms_bookbarrow.lma_memberID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrow_Fine,book.LMS_bookName,book.LMS_booklId,lms_users.LMS_UserFname FROM `lms_bookbarrow` JOIN `book`ON lms_bookbarrow.bookID = book.LMS_booklId JOIN lms_users ON lms_users.user_name =lms_bookbarrow.lma_memberID WHERE `book_issuID` = '" + IssuID + "'";
            pst = con.prepareStatement(getBorrowDate);
            rs = pst.executeQuery();
            if (rs.next()) {
                MemberName.setText(rs.getString("LMS_UserFname"));
                IssuSetID.setText(rs.getString("book_issuID"));
                BookName.setText(rs.getString("LMS_bookName"));
                IssuFIne.setText(rs.getString("bookBarrow_Fine"));
                BookID.setText(rs.getString("LMS_booklId"));;
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    public static void LMS_ReleaseBook(Text IssuID, JFXTextField AdminID, Text BookID) {
        try {
            if (AdminID.getText().isEmpty()) {
                ShowMessage(Alert.AlertType.ERROR, "Please, Enter Admin Username !");
            } else {
                String AdminExi = "SELECT COUNT(*) FROM `lms_users` WHERE `user_name` = '" + AdminID.getText() + "' and  (`user_type` = 'SuperAdmin' or  `user_type` = 'Admin')";
                pst = con.prepareStatement(AdminExi);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int AdminCtn = (rs.getInt("COUNT(*)"));
                    if (AdminCtn >= 1) {
                        if (IssuID.getText().isEmpty()) {
                            ShowMessage(Alert.AlertType.ERROR, "Please, Select borrow row you want to release !");
                        } else {

                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Book Release");
                            alert.setHeaderText("Book Release");
                            alert.setContentText("Are you want to release this book ?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {

                                System.err.println(BookID);

                                String Relbook = "DELETE FROM `lms_bookbarrow` WHERE `book_issuID` = '" + IssuID.getText() + "'";
                                pst = con.prepareStatement(Relbook);
                                int Rel = pst.executeUpdate();

                                String UpdCmd = "UPDATE `book` SET `LMS_bookCount`= `LMS_bookCount` +1 WHERE `LMS_booklId` = '" + BookID.getText() + "'";
                                pst = con.prepareStatement(UpdCmd);
                                pst.execute();

                                if (Rel > 0) {
                                    ShowMessage(Alert.AlertType.INFORMATION, "Release Successul!");
                                    
                                } else {
                                    ShowMessage(Alert.AlertType.INFORMATION, "Release Unsuccessul!");
                                }
                            } else {
                                // ... user chose CANCEL or closed the dialog
                            }

                        }
                    } else {
                        ShowMessage(Alert.AlertType.ERROR, "Please, Invalid Admin Username !");
                    }
                } else {

                }
            }
        } catch (Exception ex) {
        }
    }
    
}
