/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import static LMS_business.LMS_Users.userN;
import LMS_business.LMS_BorrowBookDetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jayen
 */
public class FXML_OverdueInfoController implements Initializable {
  @FXML
    private TableView<LMS_BorrowBookDetails> lms_OvedueBook;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> lms_OvedueBookIssuId;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> lms_OvedueBookName;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> lms_OvedueBookReturnDate;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> lms_OvedueBookFine;

    @FXML
    private Text LMS_memberName;
    
    String MemberID = userN;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LMS_business.LMS_BookBarrowandReturn.LMS_BorrowOverdueBookView(lms_OvedueBook,MemberID);
        BorrowBookCold();
        Get_UserData();
    }   
    
    public void BorrowBookCold()
    {
        lms_OvedueBookIssuId.setCellValueFactory(new PropertyValueFactory<>("borrowBookID"));
        lms_OvedueBookName.setCellValueFactory(new PropertyValueFactory<>("borrowBookName"));
        lms_OvedueBookReturnDate.setCellValueFactory(new PropertyValueFactory<>("borrowEndDate"));
        lms_OvedueBookFine.setCellValueFactory(new PropertyValueFactory<>("borrowFine"));
    }
    
    private void Get_UserData(){
       LMS_business.LMS_Users.LMS_UserInfo(userN, LMS_memberName);
   }
}
