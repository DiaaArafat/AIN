package com.example.ain.ain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import oracle.jdbc.pool.OracleDataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddMember implements Initializable {
    OracleDataSource ods = null;
    Statement st = null;
    Connection conn = null;
    @FXML
    private Button ADD;

    @FXML
    private Button Activity;

    @FXML
    private Button Asets;

    @FXML
    private Button Home;

    @FXML
    private Button Members;

    @FXML
    private AnchorPane Slider;

    @FXML
    private Button Transaction;

    @FXML
    private DatePicker addDATE;

    @FXML
    private TextField addEMAIL;
    @FXML
    private TextField Major;

    @FXML
    private TextField addID;

    @FXML
    private TextField addNAME;

    @FXML
    private TextField addPHONE;

    @FXML
    private ComboBox addTYPE;

    @FXML
    private Button exit;

    @FXML
    private Button menu;

    @FXML
    private Button menu1;

    public  AddMember(){

        try {


            ods = new OracleDataSource();

            //jdbc:oracle:thin:@//localhost:1521/XEPDB1
            ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
            ods.setUser("c##omar");
            ods.setPassword("12345");
            conn = ods.getConnection();
            System.out.println("Connection is : ");
            System.out.println(conn);
            st = conn.createStatement();

        } catch (SQLException ex) {
        }

    }

    public void Home(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    public void open_Members(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Member.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }


    public void open_activites(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MemberACT.fxml")));
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
        ObservableList<String> type = FXCollections.observableArrayList("Regular", "VIP", "Board", "Media");
        addTYPE.setItems(type);
    }

    @FXML
    public void Addhim(ActionEvent event) throws IOException, SQLException {
        String id = addID.getText();
        String name = addNAME.getText();
        String email = addEMAIL.getText();
        String phone = addPHONE.getText();
        String memType = (String) addTYPE.getValue();
        String datereg="";
        try {
            datereg = addDATE.getValue().toString();
        }catch (NullPointerException e){

        }
        String Mj=Major.getText();
        if(id.isBlank()||name.isBlank()||email.isBlank()||phone.isBlank()||memType.isBlank()||datereg.isBlank()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Make Sure to fill all the fields");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;

        }
        if(checkifUserAlreayExist(id)){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("That id is already registered");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        conn = ods.getConnection();
        st = conn.createStatement();
        String query = "INSERT INTO PERSON (STUID,NAME,Major,Email,phone,type,expiary,points)"+"VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,id);
        ps.setString(2,name);
        ps.setString(3,Mj);
        ps.setString(4,email);
        ps.setString(5,phone);
        ps.setString(6,memType);
        ps.setString(7,datereg);
        ps.setString(8,"0");
        ps.executeUpdate();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Member has been added successfully!");
        alert.setTitle("Done");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public boolean  checkifUserAlreayExist(String studentId) {
        boolean result = false;

        try {


            conn = ods.getConnection();

            st = conn.createStatement();

            int count = 0 ;
            String query = " select email from person  WHERE stuid = ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, addID.getText());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(!resultSet.getString("email").equals("null") && !resultSet.getString("email").equals(""))
                    count ++;
            }
            if(count >0) result = true;

        } catch (Exception e) {
        }
        return result ;
    }
}
