module org.example.iplauctionapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.example.iplauctionapplication to javafx.fxml;
    exports org.example.iplauctionapplication;
}