package org.example.iplauctionapplication;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class teamViewController {

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
    private AnchorPane buttonHolder;
    @FXML
    private Label timerLabel;
    @FXML
    private Label cPlayerName;
    @FXML
    private Label cPlayerRole;
    @FXML
    private Label cPlayerAge;
    @FXML
    private Label cBasePrice;
    @FXML
    private ImageView cPlayerImage;
    @FXML
    private Label currentBid;
    private Label listN;
    private Label listP;


    private long cB = 0;
    private boolean bidStarted = false;
    private String currBid = null;
    private int ignorecount = 0;
    private List<String> ignored_teams = new ArrayList<>();
    playerModel player = new playerModel();
    private boolean next_player_enabled = false;

    public List<playerModel> players;
    public List<teamModel> teams;

    private Timeline timeline;
    private int timeSeconds = 30;

    public void startCountdown() {
        bidStarted = true;
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    timeSeconds--;
                    updateTimerLabel();
                    if (timeSeconds <= 0) {
                        timeline.stop();
                        timerLabel.setText("00");
                        bidResult();
                    }
                })
        );
        timeline.play();
    }

    private void updateTimerLabel() {
        int seconds = timeSeconds;
        timerLabel.setText(String.valueOf(seconds));
    }

    public void initialize() {
        players = DatabaseConnection.getAllPlayers();
        teams = DatabaseConnection.getAllTeams();
        if (teams != null && !teams.isEmpty()) {
            double gap = 0;
            for (teamModel team : teams) {
                setLables();
                Label teamName = new Label("Team: " + team.getTeamName());
                Button buttonBid = new Button("Bid");
                Button buttonIgnore = new Button("Ignore Player");

                buttonBid.setOnAction(this::handleBidClick);
                buttonIgnore.setOnAction(this::handleIgnoreClick);

                buttonBid.setId("bidButton_" + team.getTeamName());
                buttonIgnore.setId("ignoreButton_" + team.getTeamName());

                buttonBid.setMinSize(100, 30);
                buttonBid.setMaxSize(100, 35);
                buttonIgnore.setMinSize(100, 30);
                buttonIgnore.setMaxSize(100, 35);

                buttonHolder.getChildren().add(teamName);
                buttonHolder.getChildren().add(buttonBid);
                buttonHolder.getChildren().add(buttonIgnore);

                AnchorPane.setTopAnchor(teamName, 425.0);
                AnchorPane.setLeftAnchor(teamName, 100.0 + gap);
                AnchorPane.setTopAnchor(buttonBid, 450.0);
                AnchorPane.setLeftAnchor(buttonBid, 100.0 + gap);
                AnchorPane.setTopAnchor(buttonIgnore, 500.0);
                AnchorPane.setLeftAnchor(buttonIgnore, 100.0 + gap);
                gap += 120;
            }
        } else {
            System.out.println("No teams available.");
        }
        if(players.size()>0)
        {
            player = players.get(0);
            cPlayerName.setText(player.getPlayerName());
            cBasePrice.setText(String.valueOf(player.getBasePrice()));
            cPlayerAge.setText(String.valueOf(player.getPlayerAge()));
            cPlayerRole.setText(player.getPlayerRole());
            players.remove(0);
        }
        else
        {
            System.out.println("No players!!");
        }
    }

    public void nextPlayer(){
        if(next_player_enabled)
        {
            removeLabels();
            setLables();
            currentBid.setText("0");
            next_player_enabled = false;
            cB = 0;
            timeSeconds = 30;
            timerLabel.setText("30");
            currBid = null;
            ignorecount = 0;
            ignored_teams = new ArrayList<>();
            player = new playerModel();
            if(players.size()>0)
            {
                player = players.get(0);
                cPlayerName.setText(player.getPlayerName());
                cBasePrice.setText(String.valueOf(player.getBasePrice()));
                cPlayerAge.setText(String.valueOf(player.getPlayerAge()));
                cPlayerRole.setText(player.getPlayerRole());
                players.remove(0);
            }
            else {
                cPlayerName.setText("Null");
                cBasePrice.setText("Null");
                cPlayerAge.setText("Null");
                cPlayerRole.setText("Null");
            }
        }
    }

    public void handleBidClick(ActionEvent event)
    {
        int tL = Integer.parseInt(timerLabel.getText());
        Button clickedButton = (Button) event.getSource();
        String pressed = clickedButton.getId();
        int index = pressed.indexOf('_');
        String checkIg = "ignoreButton_"+pressed.substring(index+1);
        boolean check = true;
        for(String ig : ignored_teams)
        {
            if (Objects.equals(ig, checkIg)) {
                check = false;
                break;
            }
        }
        if(tL != 0 && check && !Objects.equals(currBid, pressed) && bidStarted)
        {
            timeSeconds = 31;
            currBid = pressed;
            if(cB == 0)
            {
                cB = player.getBasePrice();
            }
            else if(cB < 20000000)
            {
                cB += 1000000;
            }
            else if(cB < 50000000)
            {
                cB += 2000000;
            }
            else
            {
                cB += 2500000;
            }
            currentBid.setText(String.valueOf(cB));
            if(ignorecount == teams.size() - 1)
            {
                timeSeconds = 01;
            }
        }
    }
    public void handleIgnoreClick(ActionEvent event)
    {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        int index = buttonId.indexOf('_');
        String checkCurr = "bidButton_"+buttonId.substring(index+1);
        ignored_teams.add(buttonId);
        if(!checkCurr.equals(currBid) && bidStarted)
        {
            ignorecount ++;
        }
        if(ignorecount == teams.size())
        {
            timeSeconds = 01;
        }
        else if(currBid != null && ignorecount == teams.size()-1)
        {
            timeSeconds = 01;
        }
    }

    public void bidResult()
    {
        bidStarted = false;
        next_player_enabled = true;
        if (currBid != null)
        {
            System.out.println(player.getPlayerName()+": Bidding won by " + currBid + " for " + cB);
            int index = currBid.indexOf('_');
            player.setTeamName(currBid.substring(index+1));
            player.setBasePrice(cB);
            DatabaseConnection.updatePlayer(player);
            for(teamModel team: teams)
            {
                if(Objects.equals(team.getTeamName(), currBid.substring(index + 1)))
                {
                    team.setPurse(team.getPurse()-cB);
                    DatabaseConnection.updateTeam(team);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Player Unsold");
            players.add(player);
        }
    }
    public void setLables()
    {
        double gp = 50;
        for(teamModel team : teams)
        {
            listN = new Label(team.getTeamName());
            listP = new Label(String.valueOf(team.getPurse()));
            listN.setId("listN"+team.getTeamName());
            listP.setId("listP"+team.getTeamName());

            buttonHolder.getChildren().add(listN);
            buttonHolder.getChildren().add(listP);

            AnchorPane.setTopAnchor(listN, 155.0 + gp);
            AnchorPane.setLeftAnchor(listN, 660.0);
            AnchorPane.setTopAnchor(listP, 155.0 + gp);
            AnchorPane.setLeftAnchor(listP, 750.0);

            gp += 50;
        }
    }
    public void removeLabels() {
        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : buttonHolder.getChildren()) {
            if (node instanceof Label) {
                String id = node.getId();
                if (id != null && (id.startsWith("listN") || id.startsWith("listP"))) {
                    nodesToRemove.add(node);
                }
            }
        }
        buttonHolder.getChildren().removeAll(nodesToRemove);
    }
}