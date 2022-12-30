package com.example.ain.ain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class SignInController {
    OracleDataSource ods = null;
    Statement st = null;
    Connection conn = null;
    private VBox vbox;
    private Parent fxml;
    @FXML
    private TextField ID;
    @FXML
    private PasswordField Pass;
    @FXML
    private Button SignUp;

    @FXML
    public void Signin_Btn(ActionEvent event) throws IOException {

        if (ID.getText().equals("") || Pass.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("please make sure to fill all fields");
            alert.showAndWait();
            // return;
        } else {

            //System.out.println(ID.getText() + Pass.getText());
            String username = getUSer(ID.getText(), Pass.getText());
            if (username.equals("null") && username.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("User Not Exist , Sign Up please !");
                alert.showAndWait();
            } else if (getUSer2(ID.getText())==false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("User with id " + ID.getText()+" not found!");
                alert.showAndWait();
            }

            else if (getPass(Pass.getText(),ID.getText())==true) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Password!");
                alert.showAndWait();
            }
             else {
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public SignInController() {


        /**
         * Creates new form Patient
         */

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


    public String getUSer(String studentId, String password) {
        String result = "";

        try {


            conn = ods.getConnection();

            st = conn.createStatement();


            String query = " select name from person  WHERE stuid = ? and password = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.getString("name").equals("null") && !resultSet.getString("name").equals(""))
                    result = resultSet.getString("name");

            }


        } catch (Exception e) {
        }
        return result;
    }
    public boolean getPass(String passwor,String id) {

        try {


            conn = ods.getConnection();

            st = conn.createStatement();


            String query = " select password from person WHERE stuid = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(!resultSet.getString("password").equals(passwor))
                    return true;
            }




        } catch (Exception e) {
        }
        return false;
    }
    boolean getUSer2(String studentId) {
        boolean result = false;

        try {

            conn = ods.getConnection();

            st = conn.createStatement();

            String query = " SELECT stuid from Person ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("stuid").equals(studentId)) {
                    return true;
                }

            }

        } catch (Exception e) {
        }
        return false;

    }
}


