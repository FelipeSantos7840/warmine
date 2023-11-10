package com.felipesantos.warmine.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    private static Team teamCollect(JsonNode teamJson){
        return new Team(teamJson.get("name").asText(),teamJson.get("score").asInt());
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

    private static Player playerCollect(JsonNode playerJson){
        String name = playerJson.get("name").asText();
        String team_name = playerJson.get("team_name").asText();
        if(MinecraftData.warmine.getTeams().isEmpty()){
            return new Player(name,null);
        } else {
            return new Player(name,WarMineData.getTeam(team_name));
        }
    }

    public static void saveTeams(List<Team> teams){
        ObjectNode rootNode = mapperJson.createObjectNode();
        ArrayNode teamsNode = mapperJson.createArrayNode();
        ObjectNode auxiliarNode;

        for(Team team : teams){
            auxiliarNode = mapperJson.createObjectNode();
            auxiliarNode.put("name",team.getName());
            auxiliarNode.put("score",team.getScore());
            teamsNode.add(auxiliarNode);
        }

        rootNode.set("teams",teamsNode);
        JsonManipulator.writeJson("teams.json",rootNode);
    }

    public static void savePlayers(List<Player> players){
        ObjectNode rootNode = mapperJson.createObjectNode();
        ArrayNode playersNode = mapperJson.createArrayNode();
        ObjectNode auxiliarNode;

        for(Player player : players){
            auxiliarNode = mapperJson.createObjectNode();
            auxiliarNode.put("name",player.getName());
            auxiliarNode.put("team_name",(player.getTeam()!=null?player.getTeam().getName():"NullTeam"));
            playersNode.add(auxiliarNode);
        }

        rootNode.set("players",playersNode);
        JsonManipulator.writeJson("players.json",rootNode);
    }

    private static void writeJson(String jsonName,ObjectNode rootNode){
        try{
            mapperJson.writeValue(FileManipuler.warmineJSONFile(jsonName),rootNode);;
        }catch (IOException e){
            System.out.println("Error Log: "+e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}
