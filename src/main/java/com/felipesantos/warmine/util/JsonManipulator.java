package com.felipesantos.warmine.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.Team;
import com.felipesantos.warmine.entities.WarMineData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonManipulator {

    private static ObjectMapper mapperJson = new ObjectMapper();

    static {
        mapperJson.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public static List<Team> teamsCollect(){
        List<Team> teams = new ArrayList<>();
        File jsonFile = FileManipuler.warmineJSONFile("teams.json");
        if(jsonFile.exists()){
            try {
                JsonNode jsonNode = mapperJson.readTree(jsonFile);
                for (JsonNode nodeTeam : jsonNode.get("teams")) {
                    teams.add(JsonManipulator.teamCollect(nodeTeam));
                }
                return teams;
            } catch (IOException | NullPointerException e) {
                return new ArrayList<>();
            }
        } else {
            return teams;
        }
    }

    public static List<Player> playersCollect(){
        List<Player> players = new ArrayList<>();
        File jsonFile = FileManipuler.warmineJSONFile("players.json");
        System.out.println("Log: Get File = "+jsonFile.getPath());
        if(jsonFile.exists()){
            System.out.println("Log: File Exists");
            try {
                JsonNode jsonNode = mapperJson.readTree(jsonFile);
                for (JsonNode nodePlayers : jsonNode.get("players")) {
                    players.add(JsonManipulator.playerCollect(nodePlayers));
                }
                System.out.println("Log: " + players.size() + " players");
                return players;
            } catch (IOException | NullPointerException e){
                System.out.println("Log: Excpetion Throwed");
                System.out.println(e.getLocalizedMessage());
                return new ArrayList<>();
            }
        } else {
            return players;
        }
    }

    private static Team teamCollect(JsonNode teamJson){
        return new Team(teamJson.get("name").asText(),teamJson.get("score").asInt());
    }

    private static Player playerCollect(JsonNode playerJson){
        System.out.println("Log: Tentando Coletar Dado");
        String name = playerJson.get("name").asText();
        System.out.println("Log: Nome Coletado!");
        String team_name = playerJson.get("team_name").asText();
        System.out.println("Log: NameTeam Coletado!");
        System.out.println("Log: TeamSize:"+ MinecraftData.warmine.getTeams().size());
        if(MinecraftData.warmine.getTeams().isEmpty()){
            System.out.println("Log: Times Vazios");
            return new Player(name,null);
        } else {
            System.out.println("Log: Times com Dados");
            System.out.println("Log: User Team Name = "+team_name);
            System.out.print("Log: ");
            for(Team team : MinecraftData.warmine.getTeams()){
                System.out.print(team.getName()+", ");
            }
            List<Team> teamsResult = MinecraftData.warmine.getTeams().stream().filter((team)-> team.getName().equals(team_name)).collect(Collectors.toList());
            System.out.println("Log:" + teamsResult.size() + " teams result");
            if(teamsResult.size() != 1){
                return new Player(name,null);
            }
            return new Player(name,teamsResult.get(0));
        }
    }

    /*public static boolean teamsToJson(List<Team> teams){

    }*/
}
