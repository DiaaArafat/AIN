package com.example.ain.ain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class SignUpController  {
    OracleDataSource ods = null ;
    Statement st = null ;
    Connection conn= null ;
    private VBox vbox;
    private Parent fxml;
        @FXML
        private TextField ID;
        @FXML
        private TextField Email;
        @FXML
        private PasswordField Pass;
        @FXML
        private Button SignUp;
    @FXML
    public void Signup_Btn(ActionEvent event) {

        if (ID.getText().equals("") || Email.getText().equals("")  || Pass.getText().equals("") ) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("please make sure to fill all fields");
            alert.showAndWait();
           // return;
        }


        else if (checkifUserAlreayExist(ID.getText())){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("User Already Exist Log in instead");
            alert.showAndWait();

        }
        else if (checkifUserAlreayExist(ID.getText())){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("User Already Exist Log in instead");
            alert.showAndWait();

        }

            else {
            try {
                // TODO add your handling code here:
                conn = ods.getConnection();

                st = conn.createStatement();


                String query = " UPDATE person SET email = ?, password= ? WHERE stuid = ? ";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, Email.getText());
                preparedStatement.setString(2, Pass.getText());
                preparedStatement.setString(3, ID.getText());
                int r = preparedStatement.executeUpdate();
                //   conn.commit();
                if (r > 0) {
                    System.out.println(r + " ahas been affected");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("No Student Exist with ID : " + ID.getText());
                    alert.showAndWait();
                    //  return;
                }
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
public SignUpController ()
{



    /**
     * Creates new form Patient
     */

        try {

            ods = new OracleDataSource();

            //jdbc:oracle:thin:@//localhost:1521/XEPDB1
            ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
            ods.setUser("c##omar");
            ods.setPassword("12345");
            conn=ods.getConnection();
            System.out.println("Connection is : ");
            System.out.println(conn);
            st=conn.createStatement();

        } catch (SQLException ex) {
        }





}

    public  boolean  checkifUserAlreayExist2(String studentId) {
        boolean result = false;

        try {


            conn = ods.getConnection();

            st = conn.createStatement();

            int count = 0 ;
            String query = " select email from person  WHERE stuid = ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, ID.getText());

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
public boolean  checkifUserAlreayExist(String studentId) {
    boolean result = false;

    try {


        conn = ods.getConnection();

        st = conn.createStatement();

int count = 0 ;
        String query = " select email from person  WHERE stuid = ? ";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, ID.getText());

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
