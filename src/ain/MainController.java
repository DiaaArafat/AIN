/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ain.ain;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 * @author Admin
 */
public class MainController implements Initializable {

    @FXML
    private VBox vbox;
    private Parent fxml;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.01), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) ->{
            try{
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SignIn.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(Exception ignored){

            }
        });
    }
    @FXML
    public void open_signIn (ActionEvent event){
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.4), vbox);
        t.setToX(vbox.getLayoutX() * 20);
        t.play();
        t.setOnFinished((e) ->{
            try{

                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SignIn.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(IOException ignored){

            }
        });
    }
    @FXML
    public void open_signup(ActionEvent event){
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.4), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e) ->{
            try{
                URL fxmlURL = getClass().getResource("/fxml/SignUp.fxml");
                System.out.println(fxmlURL);
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SignUp.fxml")));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch(Exception ignored){

            }
        });
    }


}
    
