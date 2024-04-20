package org.example.iplauctionapplication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class playerModel {
    private String playerName;
    private int playerAge;
    private String playerRole;
    private long basePrice;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/iplauction";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    public playerModel(String playerName, int playerAge, String playerRole, long basePrice)
    {
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerRole = playerRole;
        this.basePrice = basePrice;
    }
    public void insertPlayer(String playerName, int playerAge, String playerRole, double basePrice, String teamName) {
        String sql = "INSERT INTO players (playerName, playerAge, playerRole, basePrice) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerName);
            statement.setInt(2, playerAge);
            statement.setString(3, playerRole);
            statement.setDouble(4, basePrice);
            statement.executeUpdate();
            System.out.println("Player inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting player: " + e.getMessage());
        }
    }
}