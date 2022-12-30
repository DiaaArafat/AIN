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

public class ActivityCont implements Initializable {

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
    private Button exit;
    @FXML
    public TableColumn ID;
    @FXML
    public TableColumn Name;
    @FXML
    public TableColumn Email;
    @FXML
    public TableColumn MEDIAID;
    @FXML
    public TableColumn Points;
    @FXML
    public ComboBox fillterMembr;
    @FXML
    public TableColumn Type;
    @FXML
    public TableColumn MExpDate;
    @FXML
    public TableColumn cost;
    @FXML
    public TableColumn Medianame;
    @FXML
    private Button menu;
    @FXML
    private TextField SearchMemb;
    @FXML
    private Button menu1;
    @FXML
    private TableView Memtables;
    ObservableList<Activitey> Mem = FXCollections.observableArrayList();

    public ActivityCont() {
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
        ObservableList<String> FillterList = FXCollections.observableArrayList("Voulnteer", "Pupil" , "Sci-Researchers" , "Cultural","Sports","Medical","Technology","School");
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
        ID.setCellValueFactory(new PropertyValueFactory<Activitey, String>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Activitey, String>("name"));
        Email.setCellValueFactory(new PropertyValueFactory<Activitey, String>("fee"));
        MEDIAID.setCellValueFactory(new PropertyValueFactory<Activitey, String>("startDate"));
        Points.setCellValueFactory(new PropertyValueFactory<Activitey, String>("socName"));
        Type.setCellValueFactory(new PropertyValueFactory<Activitey, String>("numOfSub"));
        MExpDate.setCellValueFactory(new PropertyValueFactory<Activitey, String>("endDate"));
        cost.setCellValueFactory(new PropertyValueFactory<Activitey, String>("cost"));


    }
    public void open_addActvv(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AddActivity.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    public void getALL(){
        try {


           /* conn = ods.getConnection();

            st = conn.createStatement();

            String query = " select id,name,statdate,finishdate,fee,cost,societyname,MEDIAID from ACTIVITES";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Activitey p =  new Activitey();

                p.setId(resultSet.getString("stuid"));
                p.setName(resultSet.getString("name"));
                p.setStartDate(resultSet.getString("major"));
                p.setEndDate(resultSet.getString("email"));
                p.setFee(resultSet.getString("phone"));
                p.setCost(resultSet.getString("type"));
                p.setSocName(resultSet.getString("expiary"));
                p.setMediaName(resultSet.getString("points"));

                Mem.add(p);
                System.out.println(p.getName());

            }
            TableViewLoad(Mem);*/

            conn = ods.getConnection();

            st = conn.createStatement();
            Mem.clear();

            String query = " select ID,NAME,fee,startdate,societyname,participants,finishdate,cost,mediaid from ACTIVITES";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            String tmp;
            while (resultSet.next()) {
                Activitey p = new Activitey();
                p.setId(resultSet.getString("ID"));
                p.setName(resultSet.getString("NAME"));
                p.setFee(resultSet.getString("fee"));
                p.setStartDate(resultSet.getString("startdate"));
                p.setSocName(resultSet.getString("societyname"));
                p.setNumOfSub(resultSet.getString("participants"));
                p.setEndDate(resultSet.getString("finishdate"));
                p.setCost(resultSet.getString("cost"));
                tmp=(resultSet.getString("mediaid"));
                switch (tmp){
                    case "1":
                        p.setMediaName("Roa'a Arafat");
                        break;
                    case "2":
                        p.setMediaName("Ashraf Alsayeed");
                        break;

                    case "3":
                        p.setMediaName("Noor Kobari");
                        break;
                    case "4":
                        p.setMediaName("Hind AbuZarror");
                        break;
                }

                Mem.add(p);
                System.out.println(p.getFee());

            }
            TableViewLoad(Mem);


        } catch (Exception e) {
        }
    }
    public void getData(ActionEvent actionEvent) throws SQLException {
        getALL();

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
        String query = "SELECT  id,NAME,startdate,finishdate,participants,fee,cost,societyname,mediaid From activites WHERE societyname=?";
        PreparedStatement ps = conn.prepareStatement(query);
        System.out.println(a1);
        ps.setString(1,a1);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Activitey p = new Activitey();
            p.setId(resultSet.getString("id"));
            p.setName(resultSet.getString("name"));
            p.setCost(resultSet.getString("cost"));
            p.setFee(resultSet.getString("fee"));
            p.setStartDate(resultSet.getString("startdate"));
            p.setEndDate(resultSet.getString("finishdate"));
            p.setNumOfSub(resultSet.getString("participants"));
            p.setSocName(resultSet.getString("societyname"));
            String s1=resultSet.getString("mediaid");
            switch (s1){
                case "1":p.setMediaName("Roa'a Arafat");
                break;
                case "2":p.setMediaName("Saja Hamarshe");
                    break;
                case "3":p.setMediaName("Ashraf Alsayeed");
                    break;
                case "4":p.setMediaName("Noor Kobari");
                    break;

            }
            Mem.add(p);
            System.out.println(p.getName());
        }
        TableViewLoad(Mem);

    }
    private void TableViewLoad(ObservableList<Activitey> ticketData) {

        Memtables.setItems(getMem());

    }


    public ObservableList<Activitey> getMem() {
        return Mem;
    }


    public void SearchById(ActionEvent actionEvent) {
        Mem.removeAll();

        if (getMem().size() > 0 && !SearchMemb.getText().isBlank()) {
            String keyword = SearchMemb.getText();
            if (keyword.equals("")) {
                Memtables.setItems(getMem());
            } else {
                ObservableList <Activitey> filteredData = FXCollections.observableArrayList();
                for (Activitey product : getMem()) {
                    if (product.getId().contains(keyword))
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

}




