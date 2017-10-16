/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMS_business;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jayen
 */
public class LMS_LogOutandExit {
    public void LogOut(String OpenWindow,AnchorPane CloseWindow) throws Exception
    {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText("Are you sure do you want to Sign Out?");
        
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("NO");
        
        
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            CloseWindow.getScene().getWindow().hide();
            OpenWindow(OpenWindow);
    // ... user chose "One"
        }else {
    // ... user chose CANCEL or closed the dialog
        } 
    }
    
    
    public void OpenWindow(String windowToOpen) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(windowToOpen));
        Parent rootAdmin = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootAdmin));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    public void CloseApp()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Application");
        alert.setHeaderText("Are you sure do you want to exit?");

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("NO");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            Platform.exit();
    // ... user chose "One"
        }else {
    // ... user chose CANCEL or closed the dialog
        } 
    }
}
