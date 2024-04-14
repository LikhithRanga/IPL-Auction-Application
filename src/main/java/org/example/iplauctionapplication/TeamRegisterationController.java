package org.example.iplauctionapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TeamRegisterationController {
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
    private TextField teamName;
    @FXML
    private TextField teamOwner;
    String tN;
    String tO;
    public void register(ActionEvent event) throws IOException
    {
        tN = teamName.getText();
        tO = teamOwner.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText(null);
        alert.setContentText("Registration is successful!");

        // Create a button for returning to home view
        ButtonType homeButton = new ButtonType("Go To Home");
        alert.getButtonTypes().setAll(homeButton);

        // Handle button action
        Stage currentStage = (Stage) alert.getDialogPane().getScene().getWindow();
        Button homeBtn = (Button) alert.getDialogPane().lookupButton(homeButton);
        homeBtn.setOnAction(e -> {
            currentStage.close(); // Close the alert dialog
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            st.close();
            try {
                // Load the HomeView.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Show the alert
        alert.showAndWait();
    }
}
