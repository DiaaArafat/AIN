package com.example.ain.ain;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuCont implements Initializable {

    OracleDataSource ods = null;
    Statement st = null;
    Connection conn = null;
    @FXML
    private AnchorPane Slider;
    @FXML
    private Button Home;
    @FXML
    private Button Members;
    @FXML
    private Button Activity;
    @FXML
    private Button Assets;



    @FXML
    private Button exit;

    @FXML
    private Button menu;

    @FXML
    private Button menu1;
    public MainMenuCont (){
    }
    public void Home (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

    }
    public void open_Members (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Member.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    public void open_activites (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MemberACT.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    public void open_assets (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Assets.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }


        @Override
    public void initialize(URL location, ResourceBundle resources) {

        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        Slider.setTranslateX(-300);
        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(Slider);

            slide.setToX(0);
            slide.play();

            Slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                menu.setVisible(false);
                menu1.setVisible(true);
            });
        });

        menu1.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(Slider);

            slide.setToX(-300);
            slide.play();

            Slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                menu.setVisible(true);
                menu1.setVisible(false);
            });
        });




        }



}


