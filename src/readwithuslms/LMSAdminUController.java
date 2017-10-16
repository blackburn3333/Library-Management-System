/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import static LMS_business.LMS_Users.userN;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jayen
 */
public class LMSAdminUController implements Initializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    LMS_business.LMS_Windows LMS_Wins = new LMS_business.LMS_Windows();
    LMS_business.LMS_LogOutandExit LMS_CloseAndLog = new LMS_business.LMS_LogOutandExit();

    public static String userName;

    @FXML
    private AnchorPane LMS_MainUI;

    @FXML
    private JFXButton LMS_MemerInfo;

    @FXML
    private JFXButton LMS_BookInfo;

    @FXML
    private JFXButton LMS_UiClose;

    @FXML
    private Label LMS_Clock;

    @FXML
    private Label LMS_Date;

    @FXML
    private Text testeUN;

    @FXML
    private Text lms_bookCount;

    @FXML
    private Text lms_TotalbookCount;

    @FXML
    private Text lms_TotalMemberCount;

    @FXML
    private Text lms_TotalBookUsers;

    @FXML
    private Text lms_TotaladminUsers;

    @FXML
    private Text lms_TotalSuperUsers;

    public String usersetName;

    @FXML
    public void LMS_OpenAdminInfo() throws Exception {
        LMS_Wins.OptionWindos("/readwithuslms/LMS_BookReturnAndReseve.fxml", "Book Return and Reserved Details");
    }

    @FXML
    public void LMS_OpenBookInfo() throws Exception {
        LMS_Wins.OptionWindos("/readwithuslms/LMS_bookManage.fxml", "Book Management");
    }

    @FXML
    public void LMS_OpenMemInfo() throws Exception {
        LMS_Wins.OptionWindos("/readwithuslms/FXML_memberManage.fxml", "Memeber Management");
    }

    @FXML
    public void LMS_uiExit() {
        LMS_CloseAndLog.CloseApp();
    }

    @FXML
    public void LMS_SignOut() throws Exception {
        LMS_CloseAndLog.LogOut("/readwithuslms/LMS_Start.fxml", LMS_MainUI);
    }

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UI_Date();
        UI_Time();
        info_Counter();
        Get_UserData();

        //System.err.println(userName);
    }

    public void UI_Date() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        //get current date time with Date()
        Date date = new Date();
        LMS_Date.setText(dateFormat.format(date));

    }

    public void UI_Time() {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        //get current date time with Date()
        Date date = new Date();
        LMS_Clock.setText(dateFormat.format(date));
    }

    public void info_Counter() {
        LMS_business.LMS_RowCounter.LMS_Counter("book", lms_bookCount);
        LMS_business.LMS_RowCounter.LMS_SumCounter("LMS_bookCount", "book", lms_TotalbookCount);
        LMS_business.LMS_RowCounter.LMS_Counter("lms_users", lms_TotalMemberCount);
        LMS_business.LMS_RowCounter.LMS_MemTypeCounter("lms_users", lms_TotalBookUsers, "\"Member\"");
        LMS_business.LMS_RowCounter.LMS_MemTypeCounter("lms_users", lms_TotalSuperUsers, "\"SuperAdmin\"");
        LMS_business.LMS_RowCounter.LMS_MemTypeCounter("lms_users", lms_TotaladminUsers, "\"Admin\"");

    }

    private void Get_UserData() {
        LMS_business.LMS_Users.LMS_UserInfo(userN, testeUN);
    }
}
