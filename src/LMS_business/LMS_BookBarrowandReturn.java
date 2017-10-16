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
import static LMS_business.LMS_MessageBoxes.ShowMessage;
import com.jfoenix.controls.JFXButton;
import javafx.scene.text.Text;

/**
 *
 * @author Jayen
 */
public class LMS_BookBarrowandReturn {

    public static Connection con = LMS_DatabaseConnection.DBConnection();
    public static java.sql.PreparedStatement pst = null;
    public static ResultSet rs = null;

    public static void LMS_BookIssu(Text UserId, JFXTextField bookID, Text BarrowDate, Text RetDate) {
        try {
            if (bookID.getText().isEmpty()) {
                ShowMessage(Alert.AlertType.INFORMATION, "Insert Book ID !");
            } else {
                String BorrocmdBcount = "SELECT COUNT(*) FROM `lms_bookbarrow` WHERE `lma_memberID` = '" + UserId.getText() + "'";
                pst = con.prepareStatement(BorrocmdBcount);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int Bcount = rs.getInt("COUNT(*)");
                    if (Bcount >= 2) {
                        ShowMessage(Alert.AlertType.WARNING, "Your Maximum Number of book barrow Value is Excised");
                    } else {
                        String cmd = "INSERT INTO `lms_bookbarrow`(`lma_memberID`, `bookID`, `bookBarrowDate`, `bookBarrowEndDate`, `bookBarrow_AuID`) VALUES ('" + UserId.getText() + "','" + bookID.getText() + "','" + BarrowDate.getText() + "','" + RetDate.getText() + "','AdminID')";
                        pst = (PreparedStatement) con.prepareStatement(cmd);
                        int result = pst.executeUpdate();
                        if (result > 0) {
                            try {
                                String UpdCmd = "UPDATE `book` SET `LMS_bookCount`= `LMS_bookCount` -1 WHERE `LMS_booklId` = '" + bookID.getText() + "'";
                                pst = con.prepareStatement(UpdCmd);
                                pst.execute();
                            } catch (Exception ex) {
                                System.err.println(ex);
                            }
                            ShowMessage(Alert.AlertType.INFORMATION, "Book Issue successful !");
                        } else {
                            ShowMessage(Alert.AlertType.ERROR, "Book Issue Unsuccessful !");
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }

    }

    public static void LMS_BookIssuCount(JFXButton issu, Text Status, Text UserID) {
        try {
            con = LMS_DatabaseConnection.DBConnection();
            String Bcount = "SELECT COUNT(*) FROM `lms_bookbarrow` WHERE `lma_memberID` = '" + UserID.getText() + "'";

            pst = con.prepareStatement(Bcount);
            rs = pst.executeQuery();
            if (rs.next()) {
                int Count = rs.getInt("COUNT(*)");
                if (Count >= 2) {
                    issu.setDisable(true);
                    issu.setVisible(false);
                    System.out.println("Sorry");
                    Status.setText("Your Maximum Number of book barrow Value is Excised");
                }

            } else {
                issu.setDisable(false);
                issu.setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void LMS_BookResrve(Text UserId, JFXTextField bookID, Text ReseveDate, Text ReseveEndDate) {
        try {
            if (bookID.getText().isEmpty()) {
                ShowMessage(Alert.AlertType.INFORMATION, "Insert Book ID !");
            } else {
                String cmdBcount = "SELECT COUNT(*),`lms_bookResBookID` FROM `lms_bookreseve` WHERE `lms_bookMemberID` = '" + UserId.getText() + "'";
                pst = con.prepareStatement(cmdBcount);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String BookID = rs.getString("lms_bookResBookID");
                    int Count = rs.getInt("COUNT(*)");
                    if (Count >= 2) {
                        ShowMessage(Alert.AlertType.INFORMATION, "Your Maximum Number of book reseve Value is Excised");

                    } else {
                        if (bookID.getText().equalsIgnoreCase(BookID)) {
                            ShowMessage(Alert.AlertType.INFORMATION, "You Already Reseave That Book");

                        } else {
                            String cmd = "INSERT INTO `lms_bookreseve`(`lms_bookMemberID`, `lms_bookResBookID`, `lms_bookReserveDate`, `lms_bookReserveEndDate`) VALUES  ('" + UserId.getText() + "','" + bookID.getText() + "','" + ReseveDate.getText() + "','" + ReseveEndDate.getText() + "')";
                            pst = (PreparedStatement) con.prepareStatement(cmd);
                            int result = pst.executeUpdate();
                            if (result > 0) {
                                try {
                                    String UpdCmd = "UPDATE `book` SET `LMS_bookCount`= `LMS_bookCount` -1 WHERE `LMS_booklId` = '" + bookID.getText() + "'";
                                    pst = con.prepareStatement(UpdCmd);
                                    pst.execute();
                                } catch (Exception ex) {
                                    System.err.println(ex);
                                }
                                System.out.println("Value inserted successfully!");
                                ShowMessage(Alert.AlertType.INFORMATION, "Book Reseve successful !");
                            } else {
                                System.out.println("Error occured!");
                                ShowMessage(Alert.AlertType.ERROR, "Book Reseve Unsuccessful !");
                            }
                        }

                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }

    }

    public static void LMS_ResviewBook(TableView<LMS_ResBookDetails> table, String StuID) {
        ObservableList<LMS_ResBookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookreseve.lms_bookReserveEndDate,lms_bookreseve.lms_bookResBookID,book.LMS_bookName,book.LMS_bookAuthor FROM `lms_bookreseve` JOIN book on lms_bookreseve.lms_bookResBookID = book.LMS_booklId WHERE lms_bookreseve.lms_bookMemberID  = '" + StuID + "'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String LMS_booklId = rs.getString("lms_bookResBookID");
                String LMS_bookName = rs.getString("LMS_bookName");
                String LMS_bookAuthor = rs.getString("LMS_bookAuthor");
                String LMS_ResDate = rs.getString("lms_bookReserveEndDate");
                list.add(new LMS_ResBookDetails(LMS_booklId, LMS_bookName, LMS_bookAuthor, LMS_ResDate));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_BookBarrow(Text UserName, JFXTextField MemberID, JFXButton issuBtn) {
        try {
            if (MemberID.getText().isEmpty()) {
                ShowMessage(Alert.AlertType.INFORMATION, "Please Insert Member ID");
            } else {
                String cmdBcount = "SELECT * FROM `lms_users` WHERE `user_name` = '" + MemberID.getText() + "' and `user_type` = 'Member' ";
                pst = (PreparedStatement) con.prepareStatement(cmdBcount);
                rs = pst.executeQuery();
                if (rs.next()) {
                    UserName.setText(rs.getString("LMS_UserFname"));
                    String BorrocmdBcount = "SELECT COUNT(*) FROM `lms_bookbarrow` WHERE `lma_memberID` = '" + MemberID.getText() + "'";
                    pst = con.prepareStatement(BorrocmdBcount);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        int Bcount = rs.getInt("COUNT(*)");
                        if (Bcount >= 2) {
                            ShowMessage(Alert.AlertType.WARNING, "Your Maximum Number of book barrow Value is Excised");
                            issuBtn.setVisible(false);
                        }
                    } else {
                        issuBtn.setVisible(true);
                    }
                } else {
                    ShowMessage(Alert.AlertType.WARNING, "Invalid ID");
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void LMS_BookCheck(JFXTextField MemberID, JFXTextField bookID, Text BookAvilable, JFXButton issuBtn) {
        if (bookID.getText().isEmpty()) {
            ShowMessage(Alert.AlertType.INFORMATION, "Please Insert Book ID");
        } else {
            try {
                String cmdBcount = "SELECT * FROM `book` WHERE `LMS_booklId`= '" + bookID.getText() + "' and `LMS_bookCount` >= 1 ";
                pst = (PreparedStatement) con.prepareStatement(cmdBcount);
                rs = pst.executeQuery();
                if (rs.next()) {
                    BookAvilable.setText(rs.getString("LMS_bookName"));
                    issuBtn.setVisible(true);
                } else {
                    BookAvilable.setText("Book Not Avilable For Borrow");
                    issuBtn.setVisible(false);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
    }

    public static void LMS_BookIssue(JFXTextField MemberID, JFXTextField bookID, Text BarrowDate, Text ReturnDate, JFXTextField AdminID, JFXButton issuBtn) {
        try {
            if (MemberID.getText().isEmpty()) {
                ShowMessage(Alert.AlertType.INFORMATION, "Please Insert Member Username");
            } else {

                if (bookID.getText().isEmpty()) {
                    ShowMessage(Alert.AlertType.INFORMATION, "Please Insert Book ID");
                } else {
                    if (AdminID.getText().isEmpty()) {
                        ShowMessage(Alert.AlertType.INFORMATION, "Please Insert Admin Username");
                    } else {
                        String AdminSign = "SELECT COUNT(*) FROM `lms_users` WHERE `user_name` = '" + AdminID.getText() + "' and (`user_type` = 'SuperAdmin' or `user_type` = 'Admin')";
                        pst = (PreparedStatement) con.prepareStatement(AdminSign);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            int AdminFound = (rs.getInt("COUNT(*)"));
                            if (AdminFound >= 1) {
                                String bookBorrowCount = "SELECT COUNT(*) FROM `lms_bookbarrow` WHERE `lma_memberID` = '" + MemberID.getText() + "'";
                                pst = (PreparedStatement) con.prepareStatement(bookBorrowCount);
                                rs = pst.executeQuery();
                                if (rs.next()) {
                                    int BookCount = (rs.getInt("COUNT(*)"));
                                    if (BookCount >= 2) {
                                        ShowMessage(Alert.AlertType.WARNING, "Your Maximum Number of book barrow Value is Excised");
                                    } else {
                                        String cmdBcount = "SELECT COUNT(*),`lms_bookReserveID` FROM `lms_bookreseve` WHERE `lms_bookMemberID`=  '" + MemberID.getText() + "' and `lms_bookResBookID` =  '" + bookID.getText() + "'";
                                        pst = (PreparedStatement) con.prepareStatement(cmdBcount);
                                        rs = pst.executeQuery();
                                        if (rs.next()) {
                                            String ResID = (rs.getString("lms_bookReserveID"));
                                            int RowC = (rs.getInt("COUNT(*)"));
                                            if (RowC >= 1) {
                                                String DeletRes = "DELETE FROM `lms_bookreseve` WHERE `lms_bookReserveID` = '" + ResID + "'";
                                                pst = con.prepareStatement(DeletRes);
                                                pst.execute();

                                                String cmd = "INSERT INTO `lms_bookbarrow`(`lma_memberID`, `bookID`, `bookBarrowDate`, `bookBarrowEndDate`, `bookBarrow_AuID`) VALUES ('" + MemberID.getText() + "','" + bookID.getText() + "','" + BarrowDate.getText() + "','" + ReturnDate.getText() + "','" + AdminID.getText() + "')";
                                                pst = (PreparedStatement) con.prepareStatement(cmd);
                                                int result = pst.executeUpdate();
                                                ShowMessage(Alert.AlertType.INFORMATION, "Book Issu Successful !");

                                            } else {
                                                String UpdCmd = "UPDATE `book` SET `LMS_bookCount`= `LMS_bookCount` -1 WHERE `LMS_booklId` = '" + bookID.getText() + "'";
                                                pst = con.prepareStatement(UpdCmd);
                                                pst.execute();

                                                String cmd = "INSERT INTO `lms_bookbarrow`(`lma_memberID`, `bookID`, `bookBarrowDate`, `bookBarrowEndDate`, `bookBarrow_AuID`) VALUES ('" + MemberID.getText() + "','" + bookID.getText() + "','" + BarrowDate.getText() + "','" + ReturnDate.getText() + "','" + AdminID.getText() + "')";
                                                pst = (PreparedStatement) con.prepareStatement(cmd);
                                                int result = pst.executeUpdate();
                                                ShowMessage(Alert.AlertType.INFORMATION, "Book Issu Successful !");
                                            }
                                        }
                                    }
                                }
                            } else {
                                ShowMessage(Alert.AlertType.INFORMATION, "Invalid Admin UserName");
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }

//        
    }

    public static void LMS_ResviewBookView(TableView<LMS_BookReseveCOns> table) {
        ObservableList<LMS_BookReseveCOns> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookreseve.lms_bookReserveEndDate, book.LMS_bookName, book.LMS_booklId, lms_users.LMS_UserFname, lms_users.user_name FROM lms_bookreseve JOIN lms_users on lms_users.`user_name` = lms_bookreseve.`lms_bookMemberID` JOIN book ON book.LMS_booklId = lms_bookreseve.lms_bookResBookID";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Res_bookID = rs.getString("lms_bookReserveEndDate");
                String Res_bookName = rs.getString("LMS_bookName");
                String Res_MemberID = rs.getString("user_name");
                String Res_MemberName = rs.getString("LMS_UserFname");
                list.add(new LMS_BookReseveCOns(Res_bookID, Res_bookName, Res_MemberID, Res_MemberName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_ResviewSearchBookView(TableView<LMS_BookReseveCOns> table, JFXTextField SearchValue) {
        ObservableList<LMS_BookReseveCOns> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookreseve.lms_bookReserveEndDate, book.LMS_bookName, book.LMS_booklId, lms_users.LMS_UserFname, lms_users.user_name FROM lms_bookreseve JOIN lms_users on lms_users.`user_name` = lms_bookreseve.`lms_bookMemberID` JOIN book ON book.LMS_booklId = lms_bookreseve.lms_bookResBookID WHERE lms_users.user_type = \"Member\" AND concat(lms_bookreseve.lms_bookMemberID,lms_bookreseve.lms_bookResBookID,lms_bookreseve.lms_bookReserveEndDate,book.LMS_bookName,book.LMS_bookIsbn,lms_users.LMS_UserFname,lms_users.user_name) LIKE '%" + SearchValue.getText() + "%'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Res_bookID = rs.getString("lms_bookReserveEndDate");
                String Res_bookName = rs.getString("LMS_bookName");
                String Res_MemberID = rs.getString("user_name");
                String Res_MemberName = rs.getString("LMS_UserFname");
                list.add(new LMS_BookReseveCOns(Res_bookID, Res_bookName, Res_MemberID, Res_MemberName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_BorrowBookView(TableView<LMS_BorrowBookDetails> table) {
        ObservableList<LMS_BorrowBookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookbarrow.lma_memberID,lms_bookbarrow.book_issuID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrow_Fine,book.LMS_bookName,lms_bookbarrow.bookID,book.LMS_booklId FROM `lms_bookbarrow` JOIN `book` ON lms_bookbarrow.bookID = book.LMS_booklId";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Bor_IssuID = rs.getString("book_issuID");
                String Bor_IssuName = rs.getString("LMS_bookName");
                String Bor_MembName = rs.getString("lma_memberID");
                String Bor_IssuEndDate = rs.getString("bookBarrowEndDate");
                String Bor_IssuFine = rs.getString("bookBarrow_Fine");
                list.add(new LMS_BorrowBookDetails(Bor_IssuID, Bor_IssuName, Bor_IssuEndDate, Bor_IssuFine, Bor_MembName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_BorrowBookViewSearch(TableView<LMS_BorrowBookDetails> table, JFXTextField MemberID) {

        ObservableList<LMS_BorrowBookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookbarrow.lma_memberID,lms_bookbarrow.book_issuID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrow_Fine,book.LMS_bookName,lms_bookbarrow.bookID,book.LMS_booklId FROM `lms_bookbarrow` JOIN `book` ON lms_bookbarrow.bookID = book.LMS_booklId WHERE `lma_memberID` = '" + MemberID.getText() + "'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Bor_IssuID = rs.getString("book_issuID");
                String Bor_IssuName = rs.getString("LMS_bookName");
                String Bor_IssuEndDate = rs.getString("bookBarrowEndDate");
                String Bor_IssuFine = rs.getString("bookBarrow_Fine");
                String Bor_MembName = rs.getString("lma_memberID");
                list.add(new LMS_BorrowBookDetails(Bor_IssuID, Bor_IssuName, Bor_IssuEndDate, Bor_IssuFine, Bor_MembName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_BorrowOverdueBookView(TableView<LMS_BorrowBookDetails> table, String Member_ID) {
        ObservableList<LMS_BorrowBookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookbarrow.lma_memberID,lms_bookbarrow.book_issuID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrow_Fine,book.LMS_bookName,lms_bookbarrow.bookID,book.LMS_booklId FROM `lms_bookbarrow` JOIN `book` ON lms_bookbarrow.bookID = book.LMS_booklId WHERE `lma_memberID` = '" + Member_ID + "' and `bookBarrow_Fine` >= 1";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Bor_IssuID = rs.getString("book_issuID");
                String Bor_IssuName = rs.getString("LMS_bookName");
                String Bor_MembName = rs.getString("lma_memberID");
                String Bor_IssuEndDate = rs.getString("bookBarrowEndDate");
                String Bor_IssuFine = rs.getString("bookBarrow_Fine");
                list.add(new LMS_BorrowBookDetails(Bor_IssuID, Bor_IssuName, Bor_IssuEndDate, Bor_IssuFine, Bor_MembName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_FindBorrowBookView(TableView<LMS_BorrowBookDetails> table, JFXTextField SearchID) {
        ObservableList<LMS_BorrowBookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookbarrow.lma_memberID,lms_bookbarrow.book_issuID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrow_Fine,book.LMS_bookName,lms_bookbarrow.bookID,book.LMS_booklId FROM `lms_bookbarrow` JOIN `book` ON lms_bookbarrow.bookID = book.LMS_booklId WHERE concat(lms_bookbarrow.book_issuID,lms_bookbarrow.bookID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrowDate,lms_bookbarrow.bookBarrow_AuID,lms_bookbarrow.lma_memberID,lms_bookbarrow.bookBarrow_Fine) LIKE '%" + SearchID.getText() + "%'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Bor_IssuID = rs.getString("book_issuID");
                String Bor_IssuName = rs.getString("LMS_bookName");
                String Bor_MembName = rs.getString("lma_memberID");
                String Bor_IssuEndDate = rs.getString("bookBarrowEndDate");
                String Bor_IssuFine = rs.getString("bookBarrow_Fine");
                list.add(new LMS_BorrowBookDetails(Bor_IssuID, Bor_IssuName, Bor_IssuEndDate, Bor_IssuFine, Bor_MembName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_FindBorrowOverDueBookView(TableView<LMS_BorrowBookDetails> table) {
        ObservableList<LMS_BorrowBookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookbarrow.lma_memberID,lms_bookbarrow.book_issuID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrow_Fine,book.LMS_bookName,lms_bookbarrow.bookID,book.LMS_booklId FROM `lms_bookbarrow` JOIN `book` ON lms_bookbarrow.bookID = book.LMS_booklId WHERE `bookBarrow_Fine` >= 1";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Bor_IssuID = rs.getString("book_issuID");
                String Bor_IssuName = rs.getString("LMS_bookName");
                String Bor_MembName = rs.getString("lma_memberID");
                String Bor_IssuEndDate = rs.getString("bookBarrowEndDate");
                String Bor_IssuFine = rs.getString("bookBarrow_Fine");
                list.add(new LMS_BorrowBookDetails(Bor_IssuID, Bor_IssuName, Bor_IssuEndDate, Bor_IssuFine, Bor_MembName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_FindBorrowOverDueBookViewFind(TableView<LMS_BorrowBookDetails> table, JFXTextField SearchID) {
        ObservableList<LMS_BorrowBookDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT lms_bookbarrow.lma_memberID,lms_bookbarrow.book_issuID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrow_Fine,book.LMS_bookName,lms_bookbarrow.bookID,book.LMS_booklId FROM `lms_bookbarrow` JOIN `book` ON lms_bookbarrow.bookID = book.LMS_booklId WHERE `bookBarrow_Fine` >= 1 and concat(lms_bookbarrow.book_issuID,lms_bookbarrow.bookID,lms_bookbarrow.bookBarrowEndDate,lms_bookbarrow.bookBarrowDate,lms_bookbarrow.bookBarrow_AuID,lms_bookbarrow.lma_memberID,lms_bookbarrow.bookBarrow_Fine) LIKE '%" + SearchID + "%'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Bor_IssuID = rs.getString("book_issuID");
                String Bor_IssuName = rs.getString("LMS_bookName");
                String Bor_MembName = rs.getString("lma_memberID");
                String Bor_IssuEndDate = rs.getString("bookBarrowEndDate");
                String Bor_IssuFine = rs.getString("bookBarrow_Fine");
                list.add(new LMS_BorrowBookDetails(Bor_IssuID, Bor_IssuName, Bor_IssuEndDate, Bor_IssuFine, Bor_MembName));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }
}
