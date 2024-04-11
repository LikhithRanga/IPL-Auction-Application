module org.example.iplauctionapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.iplauctionapplication to javafx.fxml;
    exports org.example.iplauctionapplication;
}