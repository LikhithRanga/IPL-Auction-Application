package org.example.iplauctionapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class teamViewController {
    @FXML
    private VBox buttonHolder;

    public void initialize() {
        for(int i = 1; i <= 5; i++) {
            Button button = new Button("Button " + i);

            button.setMinSize(30, 10);
            button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            buttonHolder.setSpacing(10);

            buttonHolder.getChildren().add(button);
        }
    }
}
