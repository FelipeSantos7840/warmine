package com.felipesantos.warmine.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarTeam {
    private String name;
    private Integer score;
    private List<String> teamsInWar;


    public WarTeam(String name, Integer score){
        this.name = name;
        this.score = score;
        teamsInWar = new ArrayList<>();
    }

    public List<String> getTeamsInWar() {
        return teamsInWar;
    }

    public boolean addTeamInWar(String nameTeam){
        boolean validate = false;
        if(teamsInWar.isEmpty()){
            teamsInWar.add(nameTeam);
            validate = true;
        } else if(!(teamsInWar.contains(nameTeam))){
            teamsInWar.add(nameTeam);
            validate = true;
        }
        return validate;
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
