/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import LMS_business.LMS_BookReseveCOns;
import LMS_business.LMS_BorrowBookDetails;
import static LMS_business.LMS_MessageBoxes.ShowMessage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jayen
 */
public class LMS_BookReturnAndReseveController implements Initializable {

    @FXML
    private JFXTextField Bor_IDOVed;

    @FXML
    private TableView<LMS_BookReseveCOns> Res_BookInfo;

    @FXML
    private TableColumn<LMS_BookReseveCOns, String> Res_memberID;

    @FXML
    private TableColumn<LMS_BookReseveCOns, String> Res_MemberName;

    @FXML
    private TableColumn<LMS_BookReseveCOns, String> Res_BookName;

    @FXML
    private TableColumn<LMS_BookReseveCOns, String> Res_BookEndDate;
    
    
    @FXML
    private TableView<LMS_BorrowBookDetails> LMS_BookBarrowTable;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowID;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowMemberID;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowName;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowEdDate;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowFine;
    
     @FXML
    private Text Bor_MemberName;

    @FXML
    private Text Bor_BookName;

    @FXML
    private Text Bor_IssuID;

    @FXML
    private Text Bor_IssuFine;

    @FXML
    private Text Bor_BookID;

    @FXML
    private JFXButton Relese;
    
    public String IssuID;
    
    public String OverIssuID;
    
    @FXML
    private JFXTextField Res_ID;
    
    @FXML
    private JFXTextField Bor_ID;
    
    @FXML
    private JFXButton ReleseOVd;

    @FXML
    private Text Bor_MemberNameOveD;

    @FXML
    private Text Bor_BookNameOveD;

    @FXML
    private Text Bor_BookIDOveD;

    @FXML
    private Text Bor_IssuIDOveD;

    @FXML
    private Text Bor_IssuFineOveD;
    
    @FXML
    private TableView<LMS_BorrowBookDetails> LMS_BookOverDBarrowTable;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookOverDBarrowID;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookOverDBarrowMemberID;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookOverDBarrowName;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookOverDBarrowEdDate;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookOverDBarrowFine;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LMS_business.LMS_BookBarrowandReturn.LMS_ResviewBookView(Res_BookInfo);
        MemeberInfoColms();
        
        LMS_business.LMS_BookBarrowandReturn.LMS_BorrowBookView(LMS_BookBarrowTable);
        BorrowBookCold();
        
        LMS_business.LMS_BookBarrowandReturn.LMS_FindBorrowOverDueBookView(LMS_BookOverDBarrowTable);
        OverDueBorrowBookCold();
        
    }    
    
    
    public void MemeberInfoColms(){
        Res_memberID.setCellValueFactory(new PropertyValueFactory<>("Res_MemberID"));
        Res_MemberName.setCellValueFactory(new PropertyValueFactory<>("Res_MemberName"));
        Res_BookName.setCellValueFactory(new PropertyValueFactory<>("Res_bookName"));
        Res_BookEndDate.setCellValueFactory(new PropertyValueFactory<>("Res_bookID"));
    }
    
    @FXML
    void find_resBooks() {
        LMS_business.LMS_BookBarrowandReturn.LMS_ResviewSearchBookView(Res_BookInfo,Res_ID);
    }
    
    @FXML
    void find_NorBooks()
    {
        LMS_business.LMS_BookBarrowandReturn.LMS_FindBorrowBookView(LMS_BookBarrowTable,Bor_ID);
    }
    
    @FXML
    void find_OverNorBooks()
    {
        LMS_business.LMS_BookBarrowandReturn.LMS_FindBorrowOverDueBookViewFind(LMS_BookOverDBarrowTable,Bor_IDOVed);
    }
    
    public void BorrowBookCold()
    {
        LMS_BookBarrowID.setCellValueFactory(new PropertyValueFactory<>("borrowBookID"));
        LMS_BookBarrowName.setCellValueFactory(new PropertyValueFactory<>("borrowBookName"));
        LMS_BookBarrowMemberID.setCellValueFactory(new PropertyValueFactory<>("borrowMemberID"));
        LMS_BookBarrowEdDate.setCellValueFactory(new PropertyValueFactory<>("borrowEndDate"));
        LMS_BookBarrowFine.setCellValueFactory(new PropertyValueFactory<>("borrowFine"));
    }
    
    public void OverDueBorrowBookCold()
    {
        LMS_BookOverDBarrowID.setCellValueFactory(new PropertyValueFactory<>("borrowBookID"));
        LMS_BookOverDBarrowMemberID.setCellValueFactory(new PropertyValueFactory<>("borrowBookName"));
        LMS_BookOverDBarrowName.setCellValueFactory(new PropertyValueFactory<>("borrowMemberID"));
        LMS_BookOverDBarrowEdDate.setCellValueFactory(new PropertyValueFactory<>("borrowEndDate"));
        LMS_BookOverDBarrowFine.setCellValueFactory(new PropertyValueFactory<>("borrowFine"));
    }
    
    public void ReleseBook()
    {
        ObservableList<LMS_BorrowBookDetails> SelectedBarrow,AllBarrow;
        AllBarrow = LMS_BookBarrowTable.getItems();
        SelectedBarrow = LMS_BookBarrowTable.getSelectionModel().getSelectedItems();
        
        
        if(SelectedBarrow.isEmpty())
        {
            ShowMessage(Alert.AlertType.ERROR, "Please, Select borrow row you want to release !");
        }
        else
        {
            IssuID = LMS_BookBarrowTable.getSelectionModel().getSelectedItem().getBorrowBookID();
            LMS_business.LMS_BookRelease.LMS_GetReleaseData(IssuID,Bor_BookID,Bor_MemberName,Bor_BookName,Bor_IssuID,Bor_IssuFine);
        }
    }
    
    public void OveDueBook()
    {
        ObservableList<LMS_BorrowBookDetails> SelectedOverBarrow,AllOvedueBarrow;
        AllOvedueBarrow = LMS_BookOverDBarrowTable.getItems();
        SelectedOverBarrow = LMS_BookOverDBarrowTable.getSelectionModel().getSelectedItems();
        
        
        if(SelectedOverBarrow.isEmpty())
        {
            ShowMessage(Alert.AlertType.ERROR, "Please, Select borrow row you want to release !");
        }
        else
        {
            OverIssuID = LMS_BookOverDBarrowTable.getSelectionModel().getSelectedItem().getBorrowBookID();
            LMS_business.LMS_BookRelease.LMS_GetReleaseData(OverIssuID,Bor_BookIDOveD,Bor_MemberNameOveD,Bor_BookNameOveD,Bor_IssuIDOveD,Bor_IssuFineOveD);
        }
    }
}
