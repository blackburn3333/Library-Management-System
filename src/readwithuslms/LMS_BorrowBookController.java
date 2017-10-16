/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import LMS_business.LMS_BorrowBookDetails;
import static LMS_business.LMS_MessageBoxes.ShowMessage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jayen
 */
public class LMS_BorrowBookController implements Initializable {

    
    LMS_business.LMS_Windows Windows  = new LMS_business.LMS_Windows();
    @FXML
    private JFXTextField member_ID;

    @FXML
    private JFXButton get_member_Info;

    @FXML
    private Text member_Name;

    @FXML
    private JFXTextField book_ID;

    @FXML
    private Pane book_INFOAdd;

    @FXML
    private Text BarrowDate;

    @FXML
    private Text ReturnDate;

    @FXML
    private JFXButton book_Issu;

    @FXML
    private JFXButton Get_BookINFO;

    @FXML
    private Text book_Name;

    @FXML
    private JFXTextField admin_ID;

    @FXML
    private JFXTextField LMS_member_ID;

    @FXML
    private TableView<LMS_BorrowBookDetails> LMS_BookBarrowTable;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowID;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowName;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowEdDate;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowFine;

    @FXML
    private TableColumn<LMS_BorrowBookDetails, String> LMS_BookBarrowMemberID;

    @FXML
    private JFXButton Relese;

    public String BorrowBookID;

    public String IssuID;

    public String BookID;

    @FXML
    private Text Bor_MemberName;

    @FXML
    private Text Bor_BookName;

    @FXML
    private Text Bor_IssuID;

    @FXML
    private Text Bor_IssuFine;

    @FXML
    private JFXTextField LMS_AdminSign;

    @FXML
    private Text Bor_BookID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        LMS_business.LMS_BookBarrowandReturn.LMS_BorrowBookView(LMS_BookBarrowTable);
        BorrowBookCold();

        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String current_date = dateFormat.format(new Date());
        BarrowDate.setText(current_date);

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 14);
        dt = c.getTime();
        String RetDate = dateFormat.format(dt);
        ReturnDate.setText(RetDate);

    }

    public void refreshTable() {
        LMS_business.LMS_BookBarrowandReturn.LMS_BorrowBookView(LMS_BookBarrowTable);
        BorrowBookCold();
    }

    @FXML
    void SearchBarrowBook() {
        if (LMS_member_ID.getText().isEmpty()) {
            LMS_business.LMS_BookBarrowandReturn.LMS_BorrowBookView(LMS_BookBarrowTable);
        } else {
            LMS_business.LMS_BookBarrowandReturn.LMS_BorrowBookViewSearch(LMS_BookBarrowTable, LMS_member_ID);
        }
    }

    @FXML
    void issuBook() {
        LMS_business.LMS_BookBarrowandReturn.LMS_BookIssue(member_ID, book_ID, BarrowDate, ReturnDate, admin_ID, book_Issu);
    }

    @FXML
    void bookINfo() {
        LMS_business.LMS_BookBarrowandReturn.LMS_BookCheck(member_ID, book_ID, book_Name, book_Issu);
    }

    @FXML
    void get_mem_info() {
        LMS_business.LMS_BookBarrowandReturn.LMS_BookBarrow(member_Name, member_ID, book_Issu);
    }

    public void BorrowBookCold() {
        LMS_BookBarrowID.setCellValueFactory(new PropertyValueFactory<>("borrowBookID"));
        LMS_BookBarrowName.setCellValueFactory(new PropertyValueFactory<>("borrowBookName"));
        LMS_BookBarrowMemberID.setCellValueFactory(new PropertyValueFactory<>("borrowMemberID"));
        LMS_BookBarrowEdDate.setCellValueFactory(new PropertyValueFactory<>("borrowEndDate"));
        LMS_BookBarrowFine.setCellValueFactory(new PropertyValueFactory<>("borrowFine"));
    }

    public void ReleseBook() {
        ObservableList<LMS_BorrowBookDetails> SelectedBarrow, AllBarrow;
        AllBarrow = LMS_BookBarrowTable.getItems();
        SelectedBarrow = LMS_BookBarrowTable.getSelectionModel().getSelectedItems();

        if (SelectedBarrow.isEmpty()) {
            ShowMessage(Alert.AlertType.ERROR, "Please, Select borrow row you want to release !");
        } else {
            IssuID = LMS_BookBarrowTable.getSelectionModel().getSelectedItem().getBorrowBookID();
            LMS_business.LMS_BookRelease.LMS_GetReleaseData(IssuID, Bor_BookID, Bor_MemberName, Bor_BookName, Bor_IssuID, Bor_IssuFine);
        }
    }

    public void LMS_ReleaseBook() throws Exception {
        LMS_business.LMS_BookRelease.LMS_ReleaseBook(Bor_IssuID, LMS_AdminSign, Bor_BookID);
        refreshTable();
        OpenBarrow();
    }
    
    public void OpenBarrow() throws Exception
    {
        Windows.OptionWindos("/readwithuslms/FXML_MemberBookBorrow.fxml","Barrow Books");
    }
}
