package com.app.upgradingketiga;

public class TeamModel {
    private String image, teamName, stadiumName, description;

    public TeamModel(String image, String teamName, String stadiumName, String description) {
        this.image = image;
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
