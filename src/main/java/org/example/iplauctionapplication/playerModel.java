package org.example.iplauctionapplication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class playerModel {
    private String playerName;
    private int playerAge;
    private String playerRole;
    private long basePrice;

    public playerModel(String playerName, int playerAge, String playerRole, long basePrice)
    {
        System.out.println("Entered Constructor");
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerRole = playerRole;
        this.basePrice = basePrice;
    }
    public  void registerPlayer(){
        try(Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3606/IPLAuction","root","Kesh123@Keka")) {
            String sqlquery = "INSERT INTO players(playerName,playerAge,playerRole,basePrice,teamName) VALUES(playerName,playerAge,playerRole,basePrice,NULL)";
            try (PreparedStatement statement = connection.prepareStatement(sqlquery)) {
                statement.setString(1, playerName);
                statement.setInt(2, playerAge);
                statement.setString(3, playerRole);
                statement.setLong(3, basePrice);
                statement.executeUpdate();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}