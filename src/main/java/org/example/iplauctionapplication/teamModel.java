package org.example.iplauctionapplication;

import java.math.BigDecimal;
import java.sql.*;

public class teamModel {
    private String teamName;
    private String Owner;
    public long purse = 1000000000;

    public teamModel(String teamName, String Owner){
        this.teamName = teamName;
        this.Owner = Owner;
        registerTeam();
    }
    public void registerTeam()
    {
        try
        {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/iplauction",
                    "root",
                    "Kesh123@Keka"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO teams (teamName, owner, purse) VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, teamName);
            preparedStatement.setString(2, Owner);
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(purse));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Team registered successfully.");
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
