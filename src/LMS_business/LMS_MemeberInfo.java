/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import LMS_DBConnection.LMS_DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import static LMS_business.LMS_MessageBoxes.ShowMessage;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Image;
import javafx.scene.control.TableRow;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jayen
 */
public class LMS_MemeberInfo {

    public static Connection con = LMS_DatabaseConnection.DBConnection();
    public static java.sql.PreparedStatement pst = null;
    public static ResultSet rs = null;

    public static void LMS_MemeberView(TableView<LMS_MemberDetails> table) {
        ObservableList<LMS_MemberDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT * FROM `lms_users` WHERE `user_type` = \"Member\"";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String LMS_MemlId = rs.getString("user_name");
                String LMS_MemName = rs.getString("LMS_UserFname");
                String LMS_MemCont = rs.getString("user_contact");
                String LMS_MemAddress = rs.getString("user_Address");
                String LMS_MemberType = rs.getString("user_type");
                list.add(new LMS_MemberDetails(LMS_MemlId, LMS_MemName, LMS_MemCont, LMS_MemAddress,LMS_MemberType));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_MembSearch(TableView<LMS_MemberDetails> table, JFXTextField SearchVal) {
        ObservableList<LMS_MemberDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT * FROM `lms_users` WHERE `user_type` = \"Member\"  AND concat (`LMS_UserFname`,`user_name`,`user_contact`,`user_Address`) LIKE '%" + SearchVal.getText() + "%'";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String LMS_MemlId = rs.getString("user_name");
                String LMS_MemName = rs.getString("LMS_UserFname");
                String LMS_MemCont = rs.getString("user_contact");
                String LMS_MemAddress = rs.getString("user_Address");
                String LMS_MemberType = rs.getString("user_type");
                list.add(new LMS_MemberDetails(LMS_MemlId, LMS_MemName, LMS_MemCont, LMS_MemAddress,LMS_MemberType));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_MembAdd(JFXTextField lms_Mname, JFXTextField lms_Mnic, JFXTextField lms_Mcont, JFXTextField lms_Madd, ComboBox lms_Mgen, JFXPasswordField lms_MPw, String Type) {
        try {
            String UserCheck = "SELECT COUNT(*) FROM `lms_users` WHERE `user_name` = '" + lms_Mnic.getText() + "'";

            pst = (PreparedStatement) con.prepareStatement(UserCheck);
            rs = pst.executeQuery();

            if (rs.next()) {
                int CheckID = rs.getInt("COUNT(*)");
                if (CheckID >= 1) {
                    ShowMessage(Alert.AlertType.ERROR, "NIC number Already Exists !");
                } else {
                    if (!lms_Mname.getText().isEmpty() && !lms_Mnic.getText().isEmpty() && !lms_Mcont.getText().isEmpty() && !lms_Madd.getText().isEmpty()) {
                        if (!lms_Mgen.getSelectionModel().isEmpty()) {
                            String cmd = "INSERT INTO `lms_users`(`LMS_UserFname`, `user_name`, `user_contact`, `user_Address`, `user_gender`, `user_password`, `user_type`) VALUES ('" + lms_Mname.getText() + "','" + lms_Mnic.getText() + "','" + lms_Mcont.getText() + "','" + lms_Madd.getText() + "','" + lms_Mgen.getValue().toString() + "','" + lms_MPw.getText() + "','" + Type + "')";
                            pst = (PreparedStatement) con.prepareStatement(cmd);
                            int result = pst.executeUpdate();
                            if (result > 0) {
                                System.out.println("Memeber inserted successfully!");
                                ShowMessage(Alert.AlertType.INFORMATION, "Memeber inserted successfully!");
                                lms_Mname.clear();
                                lms_Mnic.clear();
                                lms_Madd.clear();
                                lms_Mgen.getSelectionModel().select(-1);
                                lms_Mcont.clear();
                                lms_MPw.clear();
                            } else {
                                System.out.println("Error occured!");
                                ShowMessage(Alert.AlertType.ERROR, "Error occurred!");
                            }
                        } else {

                            ShowMessage(Alert.AlertType.ERROR, "Please select gender!");
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

    public static void SearchMember(JFXTextField SearchVal, JFXTextField nic, JFXTextField name, JFXTextField cont, ComboBox gender, JFXTextField address, JFXPasswordField pass) {
        try {
            if (SearchVal.getText().isEmpty()) {
                ShowMessage(Alert.AlertType.INFORMATION, "Please Insert Member NIC");
            } else {

                String cmd = "SELECT * FROM `lms_users` WHERE `user_name`  = '" + SearchVal.getText() + "'";
                pst = (PreparedStatement) con.prepareStatement(cmd);
                rs = pst.executeQuery();

                if (rs.next()) {
                    nic.setPromptText(rs.getString("user_name"));
                    name.setPromptText(rs.getString("LMS_UserFname"));
                    cont.setPromptText(rs.getString("user_contact"));
                    address.setPromptText(rs.getString("user_Address"));
                    pass.setPromptText(rs.getString("user_password"));
                    gender.setPromptText(rs.getString("user_gender"));

                    nic.setText(rs.getString("user_name"));
                    name.setText(rs.getString("LMS_UserFname"));
                    cont.setText(rs.getString("user_contact"));
                    address.setText(rs.getString("user_Address"));
                    pass.setText(rs.getString("user_password"));
                    gender.setValue(rs.getString("user_gender"));

                } else {
                    ShowMessage(Alert.AlertType.WARNING, "Invalid NIC");
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void UpdateMember(JFXTextField SearchVal, JFXTextField nic, JFXTextField name, JFXTextField cont, ComboBox gender, JFXTextField address, JFXPasswordField pass) {

        try {
            if (!nic.getText().isEmpty() && !name.getText().isEmpty() && !cont.getText().isEmpty() && !address.getText().isEmpty()) {
                String cmd = "UPDATE `lms_users` SET `LMS_UserFname`='" + name.getText() + "',`user_name`='" + nic.getText() + "',`user_contact`='" + cont.getText() + "',`user_Address`='" + address.getText() + "',`user_gender`='" + gender.getValue().toString() + "',`user_password`='" + pass.getText() + "' WHERE `user_name` = '" + SearchVal.getText() + "'";
                pst = (PreparedStatement) con.prepareStatement(cmd);
                int result = pst.executeUpdate();
                if (result > 0) {
                    System.out.println("Memeber updated successfully!");
                    ShowMessage(Alert.AlertType.INFORMATION, "Memeber updated successfully!");
                    SearchVal.clear();
                    nic.clear();
                    name.clear();
                    cont.clear();
                    pass.clear();
                    address.clear();
                    gender.setPromptText("Select Gender");
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

    public static void LMS_DeleteMember(JFXTextField SearchVal, JFXTextField nic, JFXTextField name, JFXTextField cont, ComboBox gender, JFXTextField address, JFXPasswordField pass) {
        try {
            if (!SearchVal.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText(null);
                alert.setContentText("Are you want to delete Member?");
                Optional<ButtonType> confirm = alert.showAndWait();
                if (confirm.isPresent() && confirm.get() == ButtonType.YES) {
                    String cmd = "DELETE FROM `lms_users` WHERE `user_name`  ='" + SearchVal.getText() + "'";
                    pst = (PreparedStatement) con.prepareStatement(cmd);
                    int result = pst.executeUpdate();
                    if (result > 0) {
                        ShowMessage(Alert.AlertType.INFORMATION, "Successfully deleted!");
                        SearchVal.clear();
                        nic.clear();
                        name.clear();
                        cont.clear();
                        pass.clear();
                        address.clear();
                        gender.setPromptText("Select Gender");
                    } else {
                        ShowMessage(Alert.AlertType.ERROR, "Error occurred!");
                    }
                }
            } else {
                ShowMessage(Alert.AlertType.ERROR, "Please select member to delete!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void LMS_AdminAdd(JFXTextField lms_Mname, JFXTextField lms_Mnic, JFXTextField lms_Mcont, JFXTextField lms_Madd, ComboBox lms_Mgen, JFXPasswordField lms_MPw, ComboBox Type) {
        try {
            String UserCheck = "SELECT COUNT(*) FROM `lms_users` WHERE `user_name` = '" + lms_Mnic.getText() + "'";

            pst = (PreparedStatement) con.prepareStatement(UserCheck);
            rs = pst.executeQuery();

            if (rs.next()) {
                int CheckID = rs.getInt("COUNT(*)");
                if (CheckID >= 1) {
                    ShowMessage(Alert.AlertType.ERROR, "NIC number Already Exists !");
                } else {
                    if (!lms_Mname.getText().isEmpty() && !lms_Mnic.getText().isEmpty() && !lms_Mcont.getText().isEmpty() && !lms_Madd.getText().isEmpty()) {
                        if (!lms_Mgen.getSelectionModel().isEmpty()) {
                            String cmd = "INSERT INTO `lms_users`(`LMS_UserFname`, `user_name`, `user_contact`, `user_Address`, `user_gender`, `user_password`, `user_type`) VALUES ('" + lms_Mname.getText() + "','" + lms_Mnic.getText() + "','" + lms_Mcont.getText() + "','" + lms_Madd.getText() + "','" + lms_Mgen.getValue().toString() + "','" + lms_MPw.getText() + "','" + Type.getValue().toString() + "')";
                            pst = (PreparedStatement) con.prepareStatement(cmd);
                            int result = pst.executeUpdate();
                            if (result > 0) {
                                System.out.println("Memeber inserted successfully!");
                                ShowMessage(Alert.AlertType.INFORMATION, "Memeber inserted successfully!");
                                lms_Mname.clear();
                                lms_Mnic.clear();
                                lms_Madd.clear();
                                lms_Mgen.getSelectionModel().select(-1);
                                Type.getSelectionModel().select(-1);
                                lms_Mcont.clear();
                                lms_MPw.clear();
                            } else {
                                System.out.println("Error occured!");
                                ShowMessage(Alert.AlertType.ERROR, "Error occurred!");
                            }
                        } else {

                            ShowMessage(Alert.AlertType.ERROR, "Please select gender!");
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

    public static void LMS_AdminView(TableView<LMS_MemberDetails> table) {
        ObservableList<LMS_MemberDetails> list = FXCollections.observableArrayList();

        try {

            String cmd = "SELECT * FROM `lms_users` WHERE `user_type` = \"Admin\" or `user_type` = \"SuperAdmin\" ORDER BY `user_type` = 'SuperAdmin' DESC";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String LMS_MemlId = rs.getString("user_name");
                String LMS_MemName =  rs.getString("LMS_UserFname");
                String LMS_MemCont = rs.getString("user_contact");
                String LMS_MemAddress = rs.getString("user_Address");
                String LMS_MemberType = rs.getString("user_type");
                list.add(new LMS_MemberDetails(LMS_MemlId, LMS_MemName, LMS_MemCont, LMS_MemAddress,LMS_MemberType));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

    public static void LMS_AdminSearch(TableView<LMS_MemberDetails> table, JFXTextField SearchVal) {
        ObservableList<LMS_MemberDetails> list = FXCollections.observableArrayList();
        try {
            String cmd = "SELECT * FROM `lms_users` WHERE concat (`LMS_UserFname`,`user_name`,`user_contact`,`user_Address`,`user_type`) LIKE '%" + SearchVal.getText() + "%' and (`user_type` = \"Admin\" or `user_type` = \"SuperAdmin\")";
            pst = (PreparedStatement) con.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                String LMS_MemlId = rs.getString("user_name");
                String LMS_MemName = rs.getString("LMS_UserFname");
                String LMS_MemCont = rs.getString("user_contact");
                String LMS_MemAddress = rs.getString("user_Address");
                String LMS_MemberType = rs.getString("user_type");
                list.add(new LMS_MemberDetails(LMS_MemlId, LMS_MemName, LMS_MemCont, LMS_MemAddress,LMS_MemberType));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        table.getItems().setAll(list);
    }

}
