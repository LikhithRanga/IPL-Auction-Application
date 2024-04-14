package org.example.iplauctionapplication;


public class playerModel {
    private String playerName;
    private int playerAge;
    private String playerRole;
    private long basePrice;

    public playerModel(String playerName, int playerAge, String playerRole, long basePrice)
    {
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerRole = playerRole;
        this.basePrice = basePrice;
    }
}