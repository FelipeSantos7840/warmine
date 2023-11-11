package com.felipesantos.warmine.entities;

import net.minecraft.scoreboard.Team;

import java.util.List;
import java.util.stream.Collectors;

public class WarMineData {

    public static final int INITIAL_SCORE = 0;
    private List<WarTeam> warTeams;
    private List<Player> players;
    private List<CrownDataBlock> capitals;

    public WarMineData(List<WarTeam> warTeams, List<Player> players, List<CrownDataBlock> capitals) {
        this.warTeams = warTeams;
        this.players = players;
        this.capitals = capitals;
    }

    public WarMineData() {
    }

    public List<WarTeam> getTeams() {
        return warTeams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<CrownDataBlock> getCapitals() {
        return capitals;
    }

    public void setTeams(List<WarTeam> warTeams) {
        this.warTeams = warTeams;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCapitals(List<CrownDataBlock> capitals) {
        this.capitals = capitals;
    }

    public static WarTeam getTeam(String teamPlayer){
        List<WarTeam> teamsResult = MinecraftData.warmine.getTeams().stream()
                .filter((team)-> team.getName().equals(teamPlayer)).collect(Collectors.toList());
        if(teamsResult.size() != 1){
            return null;
        } else {
            return teamsResult.get(0);
        }
    }

    public boolean removeTeam(String name){
        boolean teamRemoved = false;
        for(WarTeam warTeam : warTeams){
            if(warTeam.getName().equalsIgnoreCase(name)){
                removeTeamOfPlayers(warTeam);
                warTeams.remove(warTeam);
                teamRemoved = true;
            }
        }
        return teamRemoved;
    }

    public void removeTeamOfPlayers(WarTeam warTeam){
        for(Player player : players){
            if(player.getTeam() != null){
                if(player.getTeam().getName().equalsIgnoreCase(warTeam.getName())){
                    player.setTeam(null);
                }
            }
        }
    }
}
