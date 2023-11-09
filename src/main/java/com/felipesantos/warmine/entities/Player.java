package com.felipesantos.warmine.entities;

public class Player {
    private String name;
    private Team team;

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
