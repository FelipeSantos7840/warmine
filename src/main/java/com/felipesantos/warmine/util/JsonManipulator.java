package com.felipesantos.warmine.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.felipesantos.warmine.entities.Team;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                System.out.println("Log: Team Get");
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

    /*public static boolean teamsToJson(List<Team> teams){

    }*/
}
