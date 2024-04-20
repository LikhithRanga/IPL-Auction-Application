package org.example.iplauctionapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class PlayerRegisterationController implements Initializable {

    private Stage stage;
    private PlayerModel playerModel; // Inject PlayerModel
    private String[] Roles = {"Batsman", "All-Rounder", "WicketKeeper", "Bowler"};
    private Long[] price = {2000000L, 5000000L, 10000000L, 20000000L};

    @FXML
    private TextField playerName;
    @FXML
    private TextField playerAge;
    @FXML
    private ChoiceBox<String> playerRole;
    @FXML
    private ChoiceBox<Long> basePrice;

    public PlayerRegisterationController(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
    public void backToHome(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submit(ActionEvent event) throws IOException{
        String pN = playerName.getText();
        int pA = Integer.parseInt(playerAge.getText());
        String pR = playerRole.getValue();
        long bP= basePrice.getValue();

        // Call registerPlayer method of PlayerModel to insert into the database
        playerModel.registerPlayer(pN, pA, pR, bP);

        // Create an alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText(null);
        alert.setContentText("Registration is successful!");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerRole.getItems().addAll(Roles);
        basePrice.getItems().addAll(price);
    }
}