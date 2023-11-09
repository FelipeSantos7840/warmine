package com.felipesantos.warmine.entities;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private int score;

    public Team(String name,int score){
        this.name = name;
        this.score = score;
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
