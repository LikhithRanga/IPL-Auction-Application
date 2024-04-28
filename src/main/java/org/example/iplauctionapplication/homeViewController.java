package org.example.iplauctionapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class homeViewController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void playerReg(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("PlayerRegisteration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void teamReg(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("TeamRegisteration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void adminLogin(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void teamList(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("TeamList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
