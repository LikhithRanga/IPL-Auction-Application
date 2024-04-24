package org.example.iplauctionapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class teamViewController {
    @FXML
    private AnchorPane buttonHolder;



    public void initialize() {
        double gap = 0;
        for(int i = 1; i <= 4; i++) {
            Label Teamname = new Label("Team"+i);
            Button buttonBid = new Button("Bid");
            Button buttonIgnore = new Button("Ignore Player");

            //button.setScaleX();
            buttonBid.setMinSize(100, 30);
            buttonBid.setMaxSize(100, 35);
            buttonIgnore.setMinSize(100, 30);
            buttonIgnore.setMaxSize(100, 35);

            buttonHolder.getChildren().add(Teamname);
            buttonHolder.getChildren().add(buttonBid);
            buttonHolder.getChildren().add(buttonIgnore);

            AnchorPane.setTopAnchor(Teamname,425.0);
            AnchorPane.setLeftAnchor(Teamname, 100.0 + gap);
            AnchorPane.setTopAnchor(buttonBid, 450.0);
            AnchorPane.setLeftAnchor(buttonBid, 100.0 + gap);
            AnchorPane.setTopAnchor(buttonIgnore, 500.0);
            AnchorPane.setLeftAnchor(buttonIgnore, 100.0 + gap);
            gap+=120;
        }
    }
}
