package com.felipesantos.warmine.util;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.WarTeam;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.stream.Collectors;

public class MinecraftTeamsManipulator {
    public static ScorePlayerTeam createNewMinecraftTeam(String teamName){
        ScorePlayerTeam newTeam = MinecraftData.score.createTeam(teamName);
        return newTeam;
    }

    public static void removeMinecraftTeam(String name, Scoreboard score){
        ScorePlayerTeam team = score.getTeam(name);
        if(team != null){
            score.removeTeam(team);
        }
    }

    public static void removeDataTeam(String nameTeam){
        List<Team> deleteTeam = MinecraftData.score.getTeams().stream().filter((team) -> team.getName().equalsIgnoreCase(nameTeam))
                .collect(Collectors.toList());
        if(!deleteTeam.isEmpty()){
            for(Team team : deleteTeam){
                removeMinecraftTeam(team.getName(),MinecraftData.score);
            }
        }
        MinecraftData.warmine.removeTeam(nameTeam);
    }
}
