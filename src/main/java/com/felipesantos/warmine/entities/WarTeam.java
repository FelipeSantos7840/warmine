package com.felipesantos.warmine.entities;

public class WarTeam {
    private String name;
    private Integer score;

    public WarTeam(String name, Integer score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int score){this.score += score;}

    public void decrementScore(int score){this.score -= score;}
}
