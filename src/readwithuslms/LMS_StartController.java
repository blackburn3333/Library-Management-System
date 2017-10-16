/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import LMS_DBConnection.LMS_DatabaseConnection;
import static LMS_business.LMS_Users.userN;
import static LMS_business.LMS_Users.UserType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jayen
 */
public class LMS_StartController implements Initializable {
    LMS_business.LMS_Windows LMS_Windows = new LMS_business.LMS_Windows();
    readwithuslms.LMSAdminUController Admin = new readwithuslms.LMSAdminUController();
    LMS_business.LMS_LogOutandExit LMS_CloseAndLog = new LMS_business.LMS_LogOutandExit();
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;   
    
    @FXML
    private Pane LMS_LoginUi;
     
    @FXML
    private Text LMS_date;

    @FXML
    private Text LMS_Time;
    
    @FXML
    public JFXTextField LMS_Un;

    @FXML
    private JFXPasswordField LMS_Pw;

    @FXML
    private JFXButton LMS_SignIn;

    @FXML
    private JFXButton LMS_SignUp;
    
    @FXML
    private Label loginError;
    
    @FXML
    private JFXButton LMS_CloseBtn;
   
   
    @FXML
    void LMS_Close() {
        LMS_CloseAndLog.CloseApp();
    }
    @FXML
    void LMS_SignIn() throws Exception {
           if ((LMS_Un.getText().equals("") || LMS_Un.getText() == null)
                || (LMS_Pw.getText().equals("") || LMS_Pw.getText() == null)) {
                loginError.setText("All fields are required!");
        }else{
               if(LMS_Un.getText().equals("LMS_HeadAdmin")&&LMS_Pw.getText().equals("LMS_HeadPassword"))
               {
                   UserType = "SuperAdmin";
                   LMS_Windows.adminUI();
                   LMS_LoginUi.getScene().getWindow().hide();
               }
               else
               {
                   try{
           con = LMS_DatabaseConnection.DBConnection();
           String UserSel = "SELECT * FROM `lms_users` WHERE `user_name` = '"+LMS_Un.getText()+"' and `user_password` = '"+LMS_Pw.getText()+"'";
           pst = con.prepareStatement(UserSel);
           rs = pst.executeQuery();
           try{
           if(rs.next()){
               userN=LMS_Un.getText();
               UserType = rs.getString("user_type");
               String UserType = rs.getString("user_type");
               String Name = rs.getString("LMS_UserFname");
               //LMS_Windows.miniLogin();
               LMS_LoginUi.getScene().getWindow().hide();
               String MainAdmin = "SuperAdmin";
               String Admin_normal = "Admin";
               String Member = "Member";
               
               if(UserType.equals(MainAdmin))
               {
                   LMS_Windows.adminUI();
               }
               else if(UserType.equals(Admin_normal))
               {
                   LMS_Windows.adminUI();
               }
               else if(UserType.equals(Member))
               {
                   LMS_Windows.memberUI();
               }
               
           }else{
           loginError.setText("Username or Password Incorrect !");
           }
           }catch(SQLException ex1){
               System.out.print(ex1);
           }
           }
           catch(SQLException ex){
               System.out.print(ex);
           }
               }
    }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Date();
        Time();
        LMS_business.LMS_FineCalcuator.lms_faineCal();
        LMS_business.LMS_FineCalcuator.lms_removeResbooks();
    }    
  
   public void Date(){
       DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
    //get current date time with Date()
        Date date = new Date();
        LMS_date.setText(dateFormat.format(date));
    }
   
   public void Time(){
       DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    //get current date time with Date()
        Date date = new Date();
        LMS_Time.setText(dateFormat.format(date));
    }
   
      @FXML
    void LMS_BorrowBook() throws Exception{
        LMS_Windows.OptionWindos("/readwithuslms/LMS_BorrowBook.fxml","Barrow Books");
    }
}