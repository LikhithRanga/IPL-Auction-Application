package org.example.iplauctionapplication;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/iplauction";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Kesh123@Keka";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void executeUpdate(String query, Object... parameters) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) parameters[i]);
                } else if (parameters[i] instanceof BigDecimal) {
                    preparedStatement.setBigDecimal(i + 1, (BigDecimal) parameters[i]);
                } else {
                    preparedStatement.setString(i + 1, parameters[i].toString());
                }
            }
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Operation failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<teamModel> getAllTeams()
    {
        List<teamModel> teams = new ArrayList<>();
        String query = "SELECT * FROM teams";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                teamModel team = new teamModel();
                team.setTeamName(resultSet.getString("teamName"));
                team.setOwner(resultSet.getString("owner"));
                team.setPurse(resultSet.getLong("purse"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public static void registerPlayer(String playerName, int playerAge, String playerRole, long basePrice, String playerImage) {
        String query = "INSERT INTO players (playerName, playerAge, playerRole, basePrice, playerImage) VALUES (?, ?, ?, ?, ?)";
        executeUpdate(query, playerName, playerAge, playerRole, BigDecimal.valueOf(basePrice), playerImage);
    }

    public static void registerTeam(String teamName, String owner, long purse) {
        String query = "INSERT INTO teams (teamName, owner, purse) VALUES (?, ?, ?)";
        executeUpdate(query, teamName, owner, BigDecimal.valueOf(purse));
    }

    public static List<playerModel> getAllPlayers()
    {
        List<playerModel> players = new ArrayList<>();
        String query = "SELECT * FROM players WHERE teamName IS NULL";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                playerModel player = new playerModel();
                player.setPlayerName(resultSet.getString("playerName"));
                player.setPlayerAge(resultSet.getInt("playerAge"));
                player.setPlayerImage(resultSet.getString("playerImage"));
                player.setBasePrice(resultSet.getLong("basePrice"));
                player.setPlayerRole(resultSet.getString("playerRole"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public static void updatePlayer(playerModel player)
    {
        String name = player.getPlayerName();
        long price = player.getBasePrice();
        String team = player.getTeamName();
        String query = "Update players set basePrice = " + price + ", teamName = '" + team + "' where playerName = '" + name + "'";
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateTeam(teamModel team)
    {
        String name = team.getTeamName();
        long price = team.getPurse();
        String query = "Update teams set purse = " + price + " where teamName = '" + name + "'";
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);
            System.out.println("Rows affected: " + rowsAffected);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
