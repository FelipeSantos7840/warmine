package com.felipesantos.warmine.entities;

public class Player {
    private String name;
    private WarTeam warTeam;

    public Player(String name, WarTeam warTeam) {
        this.name = name;
        this.warTeam = warTeam;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WarTeam getTeam() {
        return warTeam;
    }

    public void setTeam(WarTeam warTeam) {
        this.warTeam = warTeam;
    }
}
