/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import com.jfoenix.controls.JFXButton;
import static LMS_business.LMS_Users.userN;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class FXML_MemberUIController implements Initializable {
    
    LMS_business.LMS_Windows LMS_Wins = new LMS_business.LMS_Windows();
    LMS_business.LMS_LogOutandExit LMS_CloseAndLog = new LMS_business.LMS_LogOutandExit();
    
    @FXML
    private AnchorPane memberUI;
    
    @FXML
    private Text LMS_UserFLname;

    @FXML
    private JFXButton LMS_SignOut;

    @FXML
    private JFXButton LMS_DataRefrest;

    @FXML
    private Label LMS_Clock;

    @FXML
    private Label LMS_Date;

    @FXML
    private JFXButton LMS_UiClose;
    
    @FXML
    private Text lms_TotalbookCount;

    @FXML
    private Text lms_bookCount;
    
    @FXML
    private Text lms_TotalbookAvilbleCount;

    @FXML
    private JFXButton lms_BarrowBooksOption;
    
    @FXML
    private Text lms_TotalbookAvilbleCopies;
    
    @FXML
    private Text USerAccessID;
     
    @FXML
    void LMS_SignOut() throws Exception {
        LMS_CloseAndLog.LogOut("/readwithuslms/LMS_Start.fxml", memberUI);
    }
    
    @FXML
    private Text lms_TotalbookBarowed;

    @FXML
    private Text lms_TotalbookFine;

    @FXML
    private JFXButton lms_BarrowBooksDetails;
    
    @FXML
    void LMS_uiExit() {
        LMS_CloseAndLog.CloseApp();
    }

    @FXML
    void info_Counter() {
        LMS_business.LMS_RowCounter.LMS_Counter("book",lms_bookCount);
        LMS_business.LMS_RowCounter.LMS_SumCounter("LMS_bookCount", "book", lms_TotalbookCount);
        LMS_business.LMS_RowCounter.LMS_AvilbleBookCounter("book", lms_TotalbookAvilbleCount);
        LMS_business.LMS_RowCounter.LMS_AvilbleCopisCounter("LMS_bookCount", "book", lms_TotalbookAvilbleCopies);
        LMS_business.LMS_RowCounter.LMS_BarrowBcount(userN, lms_TotalbookBarowed,lms_TotalbookFine);
    }
    /**
     * Initializes the controller class.
     */
    
    public void FinesOverdues(){
          Calendar cal = Calendar.getInstance();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String current_date = dateFormat.format(new Date());
          
        LMS_business.LMS_bookOverduebookFine.LMS_fineCalcu(userN,current_date);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        UI_Date();
        UI_Time();
        info_Counter();
        Get_UserData();
        FinesOverdues();
        //LMS_UserFLname.setText(userN);
        
    }    
    public void UI_Date(){
       DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
    //get current date time with Date()
        Date date = new Date();
        LMS_Date.setText(dateFormat.format(date));
    }
   
   public void UI_Time(){
       DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    //get current date time with Date()
        Date date = new Date();
        LMS_Clock.setText(dateFormat.format(date));
    }
   
   private void Get_UserData(){
       LMS_business.LMS_Users.LMS_UserInfo(userN, LMS_UserFLname);
   }
   
   @FXML
    void lms_BarrowBooks() throws Exception{
        LMS_Wins.OptionWindos("/readwithuslms/FXML_bookBarrowUI.fxml","Barrow Books");
    }
    
    @FXML
    void lms_OverDue() throws Exception{
        LMS_Wins.OptionWindos("/readwithuslms/FXML_OverdueInfo.fxml","Overdue and Fine Info");
    }
}
