package org.example.iplauctionapplication;

public class playerModel {
    private String playerName;
    private int playerAge;
    private String playerRole;
    private long basePrice;
    private String playerImage;

    public playerModel(String playerName, int playerAge, String playerRole, long basePrice, String playerImage)
    {
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerRole = playerRole;
        this.basePrice = basePrice;
        this.playerImage = playerImage;
        DatabaseConnection.registerPlayer(playerName,playerAge,playerRole,basePrice,playerImage);
    }
}