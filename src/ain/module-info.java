module com.example.ain {
    requires javafxx.controls;
    requires javafx.fxml;
    requires ojdbc8;
    requires java.sql;
    requires java.naming;


    opens com.example.ain to javafx.fxml;
    exports com.example.ain;
}