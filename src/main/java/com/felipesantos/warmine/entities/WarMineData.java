package com.felipesantos.warmine.entities;

import java.util.ArrayList;
import java.util.List;

public class WarMineData {
    List<Team> teams = new ArrayList<>();

    public WarMineData(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
