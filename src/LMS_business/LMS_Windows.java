/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jayen
 */
public class LMS_Windows {
    
    public void adminUI() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/readwithuslms/LMS_AdminU.fxml"));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootAdmin));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    public void memberUI() throws Exception{
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/readwithuslms/FXML_MemberUI.fxml"));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootAdmin));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public void OptionWindos(String sourceFile, String windowName) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sourceFile));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(windowName);
        stage.setScene(new Scene(rootAdmin));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
    
}


