package com.felipesantos.warmine.entities;

public class ConfigData {
    private Coordinate lobbyCoord;

    public ConfigData(Coordinate lobbyCoord) {
        this.lobbyCoord = lobbyCoord;
    }

    public ConfigData() {}

    public Coordinate getLobbyCoord() {
        return lobbyCoord;
    }

    public void setLobbyCoord(Coordinate lobbyCoord) {
        this.lobbyCoord = lobbyCoord;
    }
}
