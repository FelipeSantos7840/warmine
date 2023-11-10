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
        if(jsonFile.exists()){
            try {
                JsonNode jsonNode = mapperJson.readTree(jsonFile);
                for (JsonNode nodePlayers : jsonNode.get("players")) {
                    players.add(JsonManipulator.playerCollect(nodePlayers));
                }
                return players;
            } catch (IOException | NullPointerException e){
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
        String name = playerJson.get("name").asText();
        String team_name = playerJson.get("team_name").asText();
        if(MinecraftData.warmine.getTeams().isEmpty()){
            return new Player(name,null);
        } else {
            return new Player(name,JsonManipulator.getTeam(team_name));
        }
    }

    private static Team getTeam(String teamPlayer){
        List<Team> teamsResult = MinecraftData.warmine.getTeams().stream()
                .filter((team)-> team.getName().equals(teamPlayer)).collect(Collectors.toList());
        if(teamsResult.size() != 1){
            return null;
        } else {
            return teamsResult.get(0);
        }
    }

    /*public static boolean teamsToJson(List<Team> teams){

    }*/
}
