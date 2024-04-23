package org.example.iplauctionapplication;


import java.math.BigDecimal;
import java.sql.*;

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
        registerPlayer();
    }
    public  void registerPlayer(){
        try
        {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/iplauction",
                    "root",
                    "Kesh123@Keka"
            );
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("Select * from players");
//            while(resultSet.next())
//            {
//                System.out.println(resultSet.getString("playerName"));
//            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO players (playerName, playerAge, playerRole, basePrice, playerImage) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, playerName);
            preparedStatement.setInt(2, playerAge);
            preparedStatement.setString(3, playerRole);
            preparedStatement.setBigDecimal(4, BigDecimal.valueOf(basePrice));
            preparedStatement.setString(5, playerImage);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player registered successfully.");
            } else {
                System.out.println("Failed to register player.");
            }
            preparedStatement.close();
            connection.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}