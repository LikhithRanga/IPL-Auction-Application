package org.example.iplauctionapplication;

public class playerModel {
    private String playerName;
    private int playerAge;
    private String playerRole;
    private long basePrice;
    private String playerImage;
    private String teamName;

    public playerModel(String playerName, int playerAge, String playerRole, long basePrice, String playerImage)
    {
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerRole = playerRole;
        this.basePrice = basePrice;
        this.playerImage = playerImage;
        DatabaseConnection.registerPlayer(playerName,playerAge,playerRole,basePrice,playerImage);
    }

    public playerModel() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(long basePrice) {
        this.basePrice = basePrice;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}