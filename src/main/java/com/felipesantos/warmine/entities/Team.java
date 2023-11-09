package com.felipesantos.warmine.entities;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private int score;
    private List<String> players;

    public Team(String name,int score){
        this.name = name;
        this.score = score;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void addPlayers(String playerName){
        players.add(playerName);
    }

    public void removePlayer(int playerIndex){
        players.remove(playerIndex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
