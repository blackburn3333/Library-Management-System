/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readwithuslms;

import LMS_business.LMS_BookDetails;
import LMS_business.LMS_ResBookDetails;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.util.Date;
import static LMS_business.LMS_Users.userN;
/**
 * FXML Controller class
 *
 * @author Jayen
 */
public class FXML_bookBarrowUIController implements Initializable {
    
    @FXML
    private JFXButton LMS_RefreshTable;

    @FXML
    private JFXTextField LMS_bookSearchValue;

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
    private TableColumn<LMS_BookDetails, String> LMS_ColbookCount;

    
     
    @FXML
    private JFXTextField book_id;

    @FXML
    private Text book_bDate;

    @FXML
    private Text book_reDate;

    @FXML
    private JFXButton issuBook;

    @FXML
    private JFXButton check_bookAv;

    @FXML
    private Text book_avilble;
    
    @FXML
    private Text IssuBName;

    @FXML
    private Text IssuBisbn;

    @FXML
    private Text IssuBAuthor;
    @FXML
    private Text USerAccessID;
    
     @FXML
    private JFXTextField book_Rid;

    @FXML
    private Text book_RDate;

    @FXML
    private Text book_reEndDate;

    @FXML
    private JFXButton book_Reserve;

   @FXML
    private JFXButton Res_Book;

    @FXML
    private Text book_Ravilble;

    @FXML
    private Text Rbook_name;

    @FXML
    private Text Rbook_Isbn;
    
    @FXML
    private TableView<LMS_ResBookDetails> LMS_ResBookDetails;

    @FXML
    private TableColumn<LMS_ResBookDetails, String> LMS_ResBook_ID;

    @FXML
    private TableColumn<LMS_ResBookDetails, String> LMS_ResBook_name;

    @FXML
    private TableColumn<LMS_ResBookDetails, String> LMS_ResBookAut;
    
     @FXML
    private TableColumn<LMS_ResBookDetails, String> LMS_ResBookEndDate;
    
    @FXML
    void checkAvilblity() {
        LMS_business.LMS_BookInfo.LMS_viewBookChechbook(book_avilble, book_id,issuBook,IssuBName,IssuBisbn,IssuBAuthor);
    }
    @FXML
    void resCheckAvilblity() {
        LMS_business.LMS_BookInfo.LMS_viewBookChechbookRes(book_Ravilble, book_Rid,Rbook_name,Rbook_Isbn);
    }
    
    @FXML
    void LMS_bookFind() {
        LMS_business.LMS_BookInfo.LMS_BookSearch(LMS_bookInfoTable, LMS_bookSearchValue,"1");
    }
    
    @FXML
    void refreshTable() {
        LMS_business.LMS_BookInfo.LMS_viewBook(LMS_bookInfoTable,"1");
        bookInfoColms();
    }
    
    public void issueCount(){
        LMS_business.LMS_BookBarrowandReturn.LMS_BookIssuCount(issuBook,book_avilble,USerAccessID);
    }
    
    public void bookReAcc(){
          Calendar cal = Calendar.getInstance();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String current_date = dateFormat.format(new Date());
          
          //LMS_business.LMS_BookBarrowandReturn.LMS_BookAssure(current_date,USerAccessID);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        LMS_business.LMS_BookInfo.LMS_viewBook(LMS_bookInfoTable,"1");
        bookInfoColms();
        
         try {
             bookdates();
         } catch (Exception ex) {
             System.out.println(ex);
         }
         
        //issuBook.setDisable(true);
        USerAccessID.setText(userN);
        USerAccessID.setVisible(false);
        //issueCount();
        bookReAcc();
        refreshTable();
        
        try{
        LMS_business.LMS_BookBarrowandReturn.LMS_ResviewBook(LMS_ResBookDetails, userN);
        bookResInfoColms();
        
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        
        
    }    
    
    public void bookInfoColms(){
        LMS_ColbookName.setCellValueFactory(new PropertyValueFactory<>("bName"));
        LMS_ColbookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        LMS_ColbookIsbn.setCellValueFactory(new PropertyValueFactory<>("BookIsbn"));
        LMS_ColbookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        LMS_ColbookCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        LMS_ColbookCount.setCellValueFactory(new PropertyValueFactory<>("LMS_bookCount"));
    }
    
    public void bookResInfoColms(){
        LMS_ResBook_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        LMS_ResBook_name.setCellValueFactory(new PropertyValueFactory<>("bName"));
        LMS_ResBookAut.setCellValueFactory(new PropertyValueFactory<>("author"));
        LMS_ResBookEndDate.setCellValueFactory(new PropertyValueFactory<>("ResEndDate"));
    }
    
    public void bookdates() throws Exception{
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String current_date = dateFormat.format(new Date());
            //book_bDate.setText(current_date);
            book_RDate.setText(current_date);
            
            Date dt = new Date();
            Date rt = new Date();
            
            Calendar c = Calendar.getInstance(); 
            Calendar Reseve = Calendar.getInstance();
            
            c.setTime(dt); 
            Reseve.setTime(rt);
            c.add(Calendar.DATE, 14);
            Reseve.add(Calendar.DATE, 2);
            
            dt = c.getTime();
            rt = Reseve.getTime();
            
            String ResEndDate = dateFormat.format(rt);
            String RetDate = dateFormat.format(dt);
            
            //book_reDate.setText(RetDate);
            book_reEndDate.setText(ResEndDate);
            
    }
    @FXML
    void resBook() {
            LMS_business.LMS_BookBarrowandReturn.LMS_BookResrve(USerAccessID,book_Rid, book_RDate, book_reEndDate);
            LMS_business.LMS_BookInfo.LMS_viewBook(LMS_bookInfoTable,"1");
            bookInfoColms();
            LMS_business.LMS_BookBarrowandReturn.LMS_ResviewBook(LMS_ResBookDetails, userN);
            bookResInfoColms();
    }
    
    
    
}
