package ain;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws NullPointerException, IOException {


        FXMLLoader loader = new FXMLLoader();
      //  loader.load(Class.class.getClassLoader().getResource("C:/IdeaProjects/AIN/src/main/java/com/example/ain/Main.com.example.ain.resources.fxml.fxml").openStream());

try {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Main.fxml")));
    Scene scene = new Scene(root);
    scene.setFill(Color.TRANSPARENT);
    stage.setScene(scene);
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.show();
}catch (Exception e){
    System.out.println(e.getCause());

}


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}