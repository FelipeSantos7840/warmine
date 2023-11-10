package com.felipesantos.warmine.entities;

import java.util.List;
import java.util.stream.Collectors;

public class WarMineData {
    private List<Team> teams;
    private List<Player> players;

    public WarMineData(List<Team> teams, List<Player> players) {
        this.teams = teams;
        this.players = players;
    }

    public WarMineData() {
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public static Team getTeam(String teamPlayer){
        List<Team> teamsResult = MinecraftData.warmine.getTeams().stream()
                .filter((team)-> team.getName().equals(teamPlayer)).collect(Collectors.toList());
        if(teamsResult.size() != 1){
            return null;
        } else {
            return teamsResult.get(0);
        }
    }
}
