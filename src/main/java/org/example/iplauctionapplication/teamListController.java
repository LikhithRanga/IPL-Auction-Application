package org.example.iplauctionapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class teamListController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void backToHome(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TextField tName;
    @FXML
    private Label teamName;
    @FXML
    private AnchorPane buttonHolder;
    private Label TeamMem;
    private List<playerModel> players;
    String tN;
    public void enter(ActionEvent event) throws IOException{
        tN = tName.getText();
        teamName.setText(tN);
        players = new ArrayList<>();
        players = DatabaseConnection.getTeamPlayers(tN);
        removeLabels();
        setLables();
    }
    public void setLables()
    {
        double gp = 0;
        for(playerModel player: players)
        {
            TeamMem = new Label(player.getPlayerName());
            TeamMem.setId("teamMember"+String.valueOf(gp));
            TeamMem.setFont(new Font("System", 17));
            buttonHolder.getChildren().add(TeamMem);
            AnchorPane.setTopAnchor(TeamMem, 140.0 + gp);
            AnchorPane.setLeftAnchor(TeamMem, 300.0);
            gp+=30;
        }
    }
    public void removeLabels() {
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : buttonHolder.getChildren()) {
            if (node instanceof Label) {
                String id = node.getId();
                if (id != null && (id.startsWith("teamMember"))) {
                    nodesToRemove.add(node);
                }
            }
        }
        buttonHolder.getChildren().removeAll(nodesToRemove);
    }
}
