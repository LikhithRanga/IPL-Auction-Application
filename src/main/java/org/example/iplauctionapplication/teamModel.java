package org.example.iplauctionapplication;

public class teamModel {
    private String teamName;
    private String owner;
    private long purse = 1000000000;

    public teamModel(String teamName, String owner) {
        this.teamName = teamName;
        this.owner = owner;
        DatabaseConnection.registerTeam(teamName, owner, purse);
    }

    public teamModel()
    {

    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getPurse() {
        return purse;
    }

    public void setPurse(long purse) {
        this.purse = purse;
    }
}