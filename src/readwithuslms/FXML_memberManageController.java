/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import LMS_business.LMS_MemberDetails;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static LMS_business.LMS_Users.UserType;
import javafx.scene.control.TableRow;

/**
 * FXML Controller class
 *
 * @author Jayen
 */
public class FXML_memberManageController implements Initializable {

    @FXML
    private Tab AdminInfroma;

    @FXML
    private Tab MemberModifics;
    
    @FXML
    private JFXTextField LMS_AdminSearchValue;

    @FXML
    private JFXButton LMS_AdminRefreshTable;

    @FXML
    private TableView<LMS_MemberDetails> Admin_table;

    @FXML
    private TableRow<LMS_MemberDetails> Admin_table_row;

    @FXML
    private TableColumn<LMS_MemberDetails, String> Admin_colNIC;

    @FXML
    private TableColumn<LMS_MemberDetails, String> Admin_colName;

    @FXML
    private TableColumn<LMS_MemberDetails, String> Admin_colContact;

    @FXML
    private TableColumn<LMS_MemberDetails, String> Admin_colAddress;

    @FXML
    private TableColumn<LMS_MemberDetails, String> admin_Type;

    @FXML
    private JFXTextField LMS_memeberSearchValue;

    @FXML
    private JFXButton LMS_RefreshTable;

    @FXML
    private TableView<LMS_MemberDetails> member_table;

    @FXML
    private TableColumn<LMS_MemberDetails, String> member_colNIC;

    @FXML
    private TableColumn<LMS_MemberDetails, String> member_colName;

    @FXML
    private TableColumn<LMS_MemberDetails, String> member_colContact;

    @FXML
    private TableColumn<LMS_MemberDetails, String> member_colAddress;

    @FXML
    private JFXTextField lms_Mname;

    @FXML
    private JFXTextField lms_Mnic;

    @FXML
    private JFXTextField lms_Mcont;

    @FXML
    private JFXTextField lms_Madd;

    @FXML
    private JFXComboBox<String> lms_Mgen;

    @FXML
    private JFXButton lms_Minsert;

    @FXML
    private JFXPasswordField lms_MPw;

    @FXML
    private JFXTextField lms_mnameUpdate;

    @FXML
    private JFXTextField lms_mNicUpdate;

    @FXML
    private JFXTextField lms_mContUpdate;

    @FXML
    private JFXTextField lms_mAddressUpdate;

    @FXML
    private JFXComboBox<String> lms_mGenderUpdate;

    @FXML
    private JFXPasswordField lms_mpassUpdate;

    @FXML
    private JFXButton LMS_UpdateMemberInfo;

    @FXML
    private JFXButton LMS_DeleteMemberInfo;

    @FXML
    private JFXTextField LMS_memberSearchValueUpdate;

    @FXML
    private JFXButton LMS_GetMemberInfo;

    @FXML
    private Tab adminInsTab;

    @FXML
    private JFXTextField lms_Adminname;

    @FXML
    private JFXTextField lms_Adminnic;

    @FXML
    private JFXTextField lms_Admincont;

    @FXML
    private JFXTextField lms_Adminadd;

    @FXML
    private JFXComboBox<String> lms_Admingen;

    @FXML
    private JFXPasswordField lms_AdminPw;

    @FXML
    private JFXComboBox<String> lms_AdminType;

    @FXML
    private JFXButton lms_Admininsert;

    public String AdminUserType;

    public String userTypeColchange;

    @FXML
    void LMS_AddMember() {
        LMS_business.LMS_MemeberInfo.LMS_MembAdd(lms_Mname, lms_Mnic, lms_Mcont, lms_Madd, lms_Mgen, lms_MPw, "Member");
    }

    @FXML
    void LMS_AddAdmin() {
        LMS_business.LMS_MemeberInfo.LMS_AdminAdd(lms_Adminname, lms_Adminnic, lms_Admincont, lms_Adminadd, lms_Admingen, lms_AdminPw, lms_AdminType);
    }

    @FXML
    void LMS_DeleteMember() {
        LMS_business.LMS_MemeberInfo.LMS_DeleteMember(LMS_memberSearchValueUpdate, lms_mNicUpdate, lms_mnameUpdate, lms_mContUpdate, lms_mGenderUpdate, lms_mAddressUpdate, lms_mpassUpdate);
    }

    @FXML
    void LMS_memeberFind() {
        LMS_business.LMS_MemeberInfo.LMS_MembSearch(member_table, LMS_memeberSearchValue);
    }
    @FXML
    void LMS_adminFind() {
        LMS_business.LMS_MemeberInfo.LMS_AdminSearch(Admin_table, LMS_AdminSearchValue);
    }

    @FXML
    void refreshTable() {
        LMS_business.LMS_MemeberInfo.LMS_MemeberView(member_table);
        MemeberInfoColms();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.err.println("Admin Type" + UserType);
        AdminUserType = UserType;

        LMS_business.LMS_MemeberInfo.LMS_MemeberView(member_table);
        MemeberInfoColms();

        LMS_business.LMS_MemeberInfo.LMS_AdminView(Admin_table);
        AdminInfoColms();

        ObservableList<String> options = FXCollections.observableArrayList("Male", "Female");

        lms_Mgen.getItems().addAll(options);
        lms_mGenderUpdate.getItems().addAll(options);
        lms_Admingen.getItems().addAll(options);

        ObservableList<String> AdminTyps = FXCollections.observableArrayList("SuperAdmin", "Admin");
        lms_AdminType.getItems().addAll(AdminTyps);

        if (AdminUserType.equals("SuperAdmin")) {
            adminInsTab.setDisable(false);
            AdminInfroma.setDisable(false);
            MemberModifics.setDisable(false);
        } else {
            adminInsTab.setDisable(true);
            AdminInfroma.setDisable(true);
            MemberModifics.setDisable(true);
        }

    }

    public void MemeberInfoColms() {
        member_colNIC.setCellValueFactory(new PropertyValueFactory<>("MName"));
        member_colName.setCellValueFactory(new PropertyValueFactory<>("MNic"));
        member_colContact.setCellValueFactory(new PropertyValueFactory<>("MAddress"));
        member_colAddress.setCellValueFactory(new PropertyValueFactory<>("MCont"));
    }

    public void AdminInfoColms() {
        Admin_colNIC.setCellValueFactory(new PropertyValueFactory<>("MName"));
        Admin_colName.setCellValueFactory(new PropertyValueFactory<>("MNic"));
        Admin_colContact.setCellValueFactory(new PropertyValueFactory<>("MAddress"));
        Admin_colAddress.setCellValueFactory(new PropertyValueFactory<>("MCont"));
        admin_Type.setCellValueFactory(new PropertyValueFactory<>("MType"));
    }

    @FXML
    void LMS_memebrSearch() {
        LMS_business.LMS_MemeberInfo.SearchMember(LMS_memberSearchValueUpdate, lms_mNicUpdate, lms_mnameUpdate, lms_mContUpdate, lms_mGenderUpdate, lms_mAddressUpdate, lms_mpassUpdate);
    }

    @FXML
    void LMS_UpdateMember() {
        LMS_business.LMS_MemeberInfo.UpdateMember(LMS_memberSearchValueUpdate, lms_mNicUpdate, lms_mnameUpdate, lms_mContUpdate, lms_mGenderUpdate, lms_mAddressUpdate, lms_mpassUpdate);
    }
    
    @FXML
    void refreshAdminTable() {
        LMS_business.LMS_MemeberInfo.LMS_AdminView(Admin_table);
        AdminInfoColms();
    }
}
