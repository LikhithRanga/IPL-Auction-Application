package org.example.iplauctionapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class adminController {
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

    public void nextScreen(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("TeamView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    String uname;
    String pword;
    public void submit(ActionEvent event)throws IOException {
        uname = username.getText();
        pword = password.getText();
        System.out.println(uname);
        System.out.println(pword);
        if(Objects.equals(uname, "admin") && Objects.equals(pword, "admin")) {
            nextScreen(event);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Username or Password not entered properly");
            ButtonType homeButton = new ButtonType("Retry");
            alert.getButtonTypes().setAll(homeButton);

            Stage currentStage = (Stage) alert.getDialogPane().getScene().getWindow();
            Button homeBtn = (Button) alert.getDialogPane().lookupButton(homeButton);
            homeBtn.setOnAction(e -> {
                currentStage.close();
                Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                st.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            alert.showAndWait();
        }
    }
}
