package com.example.ain.ain;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;

public class MemberController implements Initializable {

    OracleDataSource ods = null;
    Statement st = null;
    Connection conn = null;
    @FXML
    private AnchorPane Slider;
    private ObservableList<ObservableList> data;
    @FXML
    private Button Home;
    @FXML
    private Button Members;
    @FXML
    private Button Activity;
    @FXML
    private Button GetData;
@FXML
private Button ADDMEMBERB;

    @FXML
    private ComboBox fillterMembr;
    @FXML

    private Button exit;
    @FXML
    public TableColumn ID;
    @FXML
    public TableColumn Name;
    @FXML
    public TableColumn Email;
    @FXML
    public TableColumn Phone;
    @FXML
    public TableColumn Points;
    @FXML
    public TableColumn Type;
    @FXML
    public TableColumn MExpDate;
    @FXML
    private Button menu;
    @FXML
    private TextField SearchMemb;
    @FXML
    private Button menu1;
    @FXML
    private TableView Memtables;
    ObservableList<Person> Mem = FXCollections.observableArrayList();
    ObservableList<String> FillterList = FXCollections.observableArrayList("Reguler" , "VIP" , "Board" , "Media");

    public MemberController() {

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
    public void open_Addmember(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AddMember.fxml")));
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
        ObservableList<String> FillterList = FXCollections.observableArrayList("Regular", "VIP" , "Board" , "Media");

        fillterMembr.setItems(FillterList);
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
        ID.setCellValueFactory(new PropertyValueFactory<Person, String>("stuid"));
        Name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        Email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        Phone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        Points.setCellValueFactory(new PropertyValueFactory<Person, String>("points"));
        MExpDate.setCellValueFactory(new PropertyValueFactory<Person, String>("mexpdate"));

    }
public void getALL(){
    try {
        conn = ods.getConnection();

        st = conn.createStatement();
        Mem.clear();
        String query = " select stuid,name,major,email,phone,type,expiary,points from person";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person p = new Person();

            p.setStuid(resultSet.getString("stuid"));
            p.setName(resultSet.getString("name"));
            p.setMajor(resultSet.getString("major"));
            p.setEmail(resultSet.getString("email"));
            p.setPhone(resultSet.getString("phone"));
            p.setType(resultSet.getString("type"));
            p.setExpiary(resultSet.getString("expiary"));
            p.setPoints(resultSet.getString("points"));
            Mem.add(p);
            System.out.println(p.getName());

        }
        TableViewLoad(Mem);


    } catch (Exception e) {
    }
}
    public void getData(ActionEvent actionEvent) throws SQLException {
       getALL();

    }

    private void TableViewLoad(ObservableList <Person> ticketData) {

        Memtables.setItems(getMem());

    }


    public ObservableList<Person> getMem() {
        return Mem;
    }


    public void SearchById(ActionEvent actionEvent) {
        Mem.removeAll();

        if (getMem().size() > 0 && !SearchMemb.getText().isBlank()) {
            String keyword = SearchMemb.getText();
            if (keyword.equals("")) {
                Memtables.setItems(getMem());
            } else {
                ObservableList<Person> filteredData = FXCollections.observableArrayList();
                for (Person product : getMem()) {
                    if (product.getStuid().contains(keyword))
                        filteredData.add(product);
                }
Memtables.getItems().setAll(Collections.emptyList());
                Memtables.setItems(filteredData);
            }
        }

        else
    {
        Memtables.setItems(getMem());

    }
    }

    public void filteredData(ActionEvent event) throws SQLException {
        if(!SearchMemb.getText().isBlank()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Make Sure to clear the Search by id field");
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.showAndWait();
        return;
        }
        Mem.clear();
        String a1= (String) fillterMembr.getValue();
        conn = ods.getConnection();
        st = conn.createStatement();
        String query = "SELECT  STUID,NAME,Major,Email,phone,type,expiary,points From person WHERE type=?";
        PreparedStatement ps = conn.prepareStatement(query);
        System.out.println(a1);
        ps.setString(1,a1);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Person p = new Person();
            p.setStuid(resultSet.getString("stuid"));
            p.setName(resultSet.getString("name"));
            p.setMajor(resultSet.getString("major"));
            p.setEmail(resultSet.getString("email"));
            p.setPhone(resultSet.getString("phone"));
            p.setType(resultSet.getString("type"));
            p.setExpiary(resultSet.getString("expiary"));
            p.setPoints(resultSet.getString("points"));
            Mem.add(p);
            System.out.println(p.getName());
        }
        TableViewLoad(Mem);

    }
}




