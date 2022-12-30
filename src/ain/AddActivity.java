package com.example.ain.ain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import oracle.jdbc.pool.OracleDataSource;

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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddActivity implements Initializable {

    OracleDataSource ods = null;
    Statement st = null;
    Connection conn = null;

    @FXML
    private Button ADDACTIVITY;

    @FXML
    private Button Activity;

    @FXML
    private DatePicker AddEndDate;

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
    private TextField addCost;

    @FXML
    private TextField addEMAILACTV;

    @FXML
    private TextField addFee;

    @FXML
    private TextField addIDACTV;

    @FXML
    private TextField addNAMEACTV;

    @FXML
    private TextField addNumberOfSubscribers;

    @FXML
    private TextField addSocietyName;

    @FXML
    private DatePicker addStartDate;

    @FXML
    private Button exit;

    @FXML
    private Button menu;

    @FXML
    private Button menu1;


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
    public  AddActivity(){

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
        } catch ( SQLException ex) {
        }

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
    }
    @FXML
    public void addActivitey(ActionEvent event) throws IOException, SQLException {
        String id = addIDACTV.getText();
        String name = addNAMEACTV.getText();
        String SocName = addSocietyName.getText();
        String mediaName = addEMAILACTV.getText();
        String fee =  addFee.getText();
        String cost=addCost.getText();
        String Num= addNumberOfSubscribers.getText();
        String strtdate = null;
        try{
           strtdate= addStartDate.getValue().toString();
        }catch(NullPointerException e){}

        String enddate = null;
        try{
           enddate= AddEndDate.getValue().toString();
        }catch(NullPointerException e){}



        if(id.isBlank()||name.isBlank()||SocName.isBlank()||mediaName.isBlank()||fee.isBlank()||cost.isBlank()||Num.isBlank()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Make Sure to fill all the fields");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;

        }
        if(checkifUserAlreayExist(id)){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("That Activity is already added");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        conn = ods.getConnection();
        st = conn.createStatement();
        String query = "INSERT INTO ACTIVITES (id,NAME,startdate,finishdate,participants,fee,cost,societyname,mediaid)"+"VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,id);
        ps.setString(2,name);
        ps.setString(3,strtdate);
        ps.setString(4,enddate);
        ps.setString(5,Num);
        ps.setString(6,fee);
        ps.setString(7,cost);
        ps.setString(8,SocName);
        ps.setString(9,mediaName);
        ps.executeUpdate();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Activity has been added successfully!");
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
            String query = " select Name from activites  WHERE id = ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, addIDACTV.getText());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(!resultSet.getString("name").isBlank())
                    count ++;
            }
            if(count >0) result = true;

        } catch (Exception e) {
        }
        return result ;
    }
}
