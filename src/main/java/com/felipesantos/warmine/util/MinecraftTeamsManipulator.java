package com.felipesantos.warmine.util;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarTeam;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;

import java.util.List;
import java.util.stream.Collectors;

public class MinecraftTeamsManipulator {
    public static ScorePlayerTeam createNewMinecraftTeam(String teamName){
        ScorePlayerTeam newTeam = MinecraftData.score.createTeam(teamName);
        newTeam.setNameTagVisibility(Team.Visible.HIDE_FOR_OTHER_TEAMS);//ESCONDER NAMETAG PARA TIME ADVERSÁRIO
        newTeam.setAllowFriendlyFire(false); //RETIRAR CASO NÃO FUNCIONAR
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

    public static Player addPlayerTeam(String nameTeam,Player player) {

        String namePlayer = player.getName();

        ScorePlayerTeam mineTeam = (ScorePlayerTeam) existMinecraftTeam(nameTeam);
        WarTeam warTeam = existWarMineTeam(nameTeam);

        if ((mineTeam != null) && (warTeam != null)) {
            mineTeam.getMembershipCollection().add(namePlayer);
            player.setTeam(warTeam);
        }
        return player;
    }

    public static boolean removePlayerTeam(Player player){
        if(player.getWarTeam() != null){
            MinecraftData.score.getTeam(player.getWarTeam().getName()).getMembershipCollection().remove(player.getName());
            player.setTeam(null);
            return true;
        } else {
            return false;
        }
    }

    public static Team existMinecraftTeam(String nameTeam){
        Team team = null;
        if(nameTeam != null){
            for(Team mineTeam : MinecraftData.score.getTeams()){
                if(mineTeam.getName().equalsIgnoreCase(nameTeam)){
                    team = mineTeam;
                    break;
                }
            }
        }
        return team;
    }

    public static WarTeam existWarMineTeam(String nameWarTeam){
        WarTeam team = null;
        if(nameWarTeam != null){
            for (WarTeam warMineTeam : MinecraftData.warmine.getTeams()){
                if(warMineTeam.getName().equalsIgnoreCase(nameWarTeam)){
                    team = warMineTeam;
                    break;
                }
            }
        }

        return team;
    }
}
