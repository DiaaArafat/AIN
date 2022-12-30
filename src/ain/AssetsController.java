package com.example.ain.ain;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssetsController implements Initializable {
private List<Item> ALLitems ;
    @FXML
    private Button Activity;

    @FXML
    private Button Asets;

    @FXML
    private GridPane Book;

    @FXML
    private Button GetData;

    @FXML
    private Button Home;

    @FXML
    private Button Members;

    @FXML
    private Button PrintReportaSSETS;

    @FXML
    private AnchorPane Slider;

    @FXML
    private Button Transaction;

    @FXML
    private Button exit;

    @FXML
    private Button menu;

    @FXML
    private Button menu1;

    private List<Item> ITEMS(){

        List<Item> LS = new ArrayList<>();
        Item item = new Item();
        item.setName("Diaa ");
        item.setCount("Fesh 3'ere");
        item.setPrice("Priceless");
        LS.add(item);
        return LS;
    }
    @FXML
    void Home(ActionEvent event) {

    }

    @FXML
    void getData(ActionEvent event) {

    }

    @FXML
    void open_Members(ActionEvent event) {

    }

    @FXML
    void open_activites(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ALLitems = new ArrayList<>(ITEMS());
        int col = 0 ;
        int row = 1 ;
        try {
            for(Item item : ALLitems)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/Book.fxml"));
                VBox ItemBox = fxmlLoader.load();
ItemController itemController = fxmlLoader.getController();
itemController.setData(item);
if(col == 6)
{
    col = 0 ;
    ++row;

}
                Book.add(ItemBox , col++ , row);
GridPane.setMargin(ItemBox , new Insets( 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            slide.setOnFinished((ActionEvent e) -> {
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

            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(true);
                menu1.setVisible(false);
            });

    });


    }
}




