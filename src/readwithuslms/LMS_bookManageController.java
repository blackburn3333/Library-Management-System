/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import LMS_business.LMS_BookDetails;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static LMS_business.LMS_Users.UserType;
/**
 * FXML Controller class
 *
 * @author Jayen
 */
public class LMS_bookManageController implements Initializable {
    
    @FXML
    private JFXTextField LMS_bookSearchValue;

    @FXML
    private JFXButton LMS_bookSearch;

    @FXML
    private TableView<LMS_BookDetails> LMS_bookInfoTable;

    @FXML
    private TableColumn<LMS_BookDetails, String> LMS_ColbookName;

    @FXML
    private TableColumn<LMS_BookDetails, String> LMS_ColbookId;

    @FXML
    private TableColumn<LMS_BookDetails, String> LMS_ColbookIsbn;

    @FXML
    private TableColumn<LMS_BookDetails, String> LMS_ColbookAuthor;

    @FXML
    private TableColumn<LMS_BookDetails, String> LMS_ColbookCatagory;

    @FXML
    private TableColumn<LMS_BookDetails, Integer> LMS_ColbookCount;
    
    @FXML
    private JFXTextField lms_bname;

    @FXML
    private JFXTextField lms_bid;

    @FXML
    private JFXTextField lms_bisbn;

    @FXML
    private JFXTextField lms_bauthor;

    @FXML
    private JFXTextField lms_bcount;

    @FXML
    private JFXComboBox lms_btype;

    @FXML
    private JFXButton lms_binsert;
    
     @FXML
    private JFXTextField lms_bnameUpdate;

    @FXML
    private JFXTextField lms_bauthorUpdate;

    @FXML
    private JFXTextField lms_bisbnUpdate;

    @FXML
    private JFXTextField lms_bidUpdate;

    @FXML
    private JFXTextField lms_bcountUpdate;

    @FXML
    private JFXComboBox lms_btypeUpdate;

    @FXML
    private JFXButton LMS_UpdateBookInfo;

    @FXML
    private JFXButton LMS_DeleteBookInfo;

    @FXML
    private JFXTextField LMS_bookSearchValueUpdate;

    @FXML
    private JFXButton LMS_GetBookInfo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        // TODO
        LMS_business.LMS_BookInfo.LMS_viewBook(LMS_bookInfoTable,"0");
        bookInfoColms();
        
        ObservableList<String> options = FXCollections.observableArrayList("Science fiction","Satire","Drama","Action and Adventure","Romance","Mystery","Horror","Self help","Health","Guide","Travel","Children","Religion, Spirituality & New Age","Science","History","Math","Anthology","Poetry","Encyclopedias","Dictionaries","Comics","Art","Cookbooks","Diaries","Journals","Prayer books","Series","Trilogy","Biographies","Autobiographies","Fantasy","Novel");
        lms_btype.getItems().addAll(options);
        lms_btypeUpdate.getItems().addAll(options);
        
        
    }    
    public void refreshTable(){
        LMS_business.LMS_BookInfo.LMS_viewBook(LMS_bookInfoTable,"0");
        bookInfoColms();
    }
    
    public void bookInfoColms(){
        LMS_ColbookName.setCellValueFactory(new PropertyValueFactory<>("bName"));
        LMS_ColbookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        LMS_ColbookIsbn.setCellValueFactory(new PropertyValueFactory<>("BookIsbn"));
        LMS_ColbookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        LMS_ColbookCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        LMS_ColbookCount.setCellValueFactory(new PropertyValueFactory<>("LMS_bookCount"));
    }
    
    @FXML
    private void LMS_bookFind(){
        //String Val = LMS_bookSearchValue.toString();
        //String FinalVal = "%"+Val+"%";
        //System.err.println(FinalVal);
        LMS_business.LMS_BookInfo.LMS_BookSearch(LMS_bookInfoTable, LMS_bookSearchValue,"0");
    }
    
    @FXML
    private void LMS_bookSearch() {
        LMS_business.LMS_BookInfo.SearchBook(LMS_bookSearchValueUpdate,lms_bidUpdate, lms_bnameUpdate, lms_bauthorUpdate, lms_btypeUpdate, lms_bcountUpdate, lms_bisbnUpdate);
    }
    
    @FXML
    void LMS_AddBook() {
            LMS_business.LMS_BookInfo.LMS_bookAdd(lms_bid, lms_bname, lms_bauthor, lms_btype, lms_bcount, lms_bisbn);
    }
    
    @FXML
    void LMS_bookUpdate() {
        LMS_business.LMS_BookInfo.updateBookinfo(LMS_bookSearchValueUpdate,lms_bidUpdate, lms_bnameUpdate, lms_bauthorUpdate, lms_btypeUpdate, lms_bcountUpdate, lms_bisbnUpdate);
    }
    
    @FXML
    void LMS_bookDelete() {
        LMS_business.LMS_BookInfo.deleteBook(LMS_bookSearchValueUpdate);
    }
}
