package com.felipesantos.warmine.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarTeam warTeam = (WarTeam) o;
        return Objects.equals(name, warTeam.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
