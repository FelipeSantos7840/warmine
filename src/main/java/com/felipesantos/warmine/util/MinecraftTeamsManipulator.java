package com.felipesantos.warmine.util;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.WarTeam;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

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
}
