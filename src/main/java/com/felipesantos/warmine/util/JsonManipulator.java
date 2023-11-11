package com.felipesantos.warmine.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.felipesantos.warmine.entities.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonManipulator {

    private static ObjectMapper mapperJson = new ObjectMapper();

    static {
        mapperJson.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public static List<WarTeam> teamsCollect(){
        List<WarTeam> warTeams = new ArrayList<>();
        File jsonFile = FileManipuler.warmineJSONFile("teams.json");
        if(jsonFile.exists()){
            try {
                JsonNode jsonNode = mapperJson.readTree(jsonFile);
                for (JsonNode nodeTeam : jsonNode.get("teams")) {
                    warTeams.add(JsonManipulator.teamCollect(nodeTeam));
                }
                return warTeams;
            } catch (IOException | NullPointerException e) {
                return new ArrayList<>();
            }
        } else {
            return warTeams;
        }
    }

    private static WarTeam teamCollect(JsonNode teamJson){
        return new WarTeam(teamJson.get("name").asText(),teamJson.get("score").asInt());
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

    public static List<CrownDataBlock> crownBlocksCollect(){
        List<CrownDataBlock> crownBlocks = new ArrayList<>();
        File jsonFile = FileManipuler.warmineJSONFile("crown_block.json");

        if(jsonFile.exists()){
            try {
                JsonNode jsonNode = mapperJson.readTree(jsonFile);
                for (JsonNode nodeCrownBlock : jsonNode.get("crown_blocks")) {
                    crownBlocks.add(JsonManipulator.crownBlockCollect(nodeCrownBlock));
                }
                return crownBlocks;
            } catch (IOException | NullPointerException e) {
                return new ArrayList<>();
            }
        } else {
            return crownBlocks;
        }
    }

    private static CrownDataBlock crownBlockCollect(JsonNode blockJson){
        String teamBlock = blockJson.get("team_name").asText();
        String nameCapital = blockJson.get("capital_name").asText();
        int x = blockJson.get("x").asInt();
        int y = blockJson.get("y").asInt();
        int z = blockJson.get("z").asInt();
        if(MinecraftData.warmine.getTeams().isEmpty()){
            return new CrownDataBlock(new Coordinate(x,y,z),WarMineData.getTeam(teamBlock),nameCapital);
        } else {
            return new CrownDataBlock(new Coordinate(x,y,z),nameCapital);
        }
    }

    public static void saveTeams(List<WarTeam> warTeams){
        ObjectNode rootNode = mapperJson.createObjectNode();
        ArrayNode teamsNode = mapperJson.createArrayNode();
        ObjectNode auxiliarNode;

        for(WarTeam warTeam : warTeams){
            auxiliarNode = mapperJson.createObjectNode();
            auxiliarNode.put("name", warTeam.getName());
            auxiliarNode.put("score", warTeam.getScore());
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

    public static void saveCrownBlocks(List<CrownDataBlock> crownBlocks){
        ObjectNode rootNode = mapperJson.createObjectNode();
        ArrayNode blocksNode = mapperJson.createArrayNode();
        ObjectNode auxiliarNode;

        for(CrownDataBlock crownBlock : crownBlocks){
            auxiliarNode = mapperJson.createObjectNode();
            auxiliarNode.put("team_name",crownBlock.getWarTeam()!=null?crownBlock.getWarTeam().getName():"NullTeam");
            auxiliarNode.put("capital_name",crownBlock.getName());
            auxiliarNode.put("x",crownBlock.getCoordinate().getX());
            auxiliarNode.put("y",crownBlock.getCoordinate().getY());
            auxiliarNode.put("z",crownBlock.getCoordinate().getZ());
            blocksNode.add(auxiliarNode);
        }

        rootNode.set("crown_blocks",blocksNode);
        JsonManipulator.writeJson("crown_block.json",rootNode);

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
