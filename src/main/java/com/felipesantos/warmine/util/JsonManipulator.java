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
import java.util.UUID;

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
        WarTeam warTeam = new WarTeam(teamJson.get("name").asText(),teamJson.get("score").asInt());
        String teamInWarName;
        for(JsonNode inWarTeam : teamJson.get("warteams_name")){
            teamInWarName = inWarTeam.asText();
            if(teamInWarName != null){
                warTeam.addTeamInWar(teamInWarName);
            }
        }
        return warTeam;
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
            return new Player(name,WarMineData.getWarTeam(team_name));
        }
    }

    public static List<CityDataBlock> cityBlocksCollect(){
        List<CityDataBlock> cityBlocks = new ArrayList<>();
        File jsonFile = FileManipuler.warmineJSONFile("city_block.json");

        if(jsonFile.exists()){
            try {
                JsonNode jsonNode = mapperJson.readTree(jsonFile);
                for(JsonNode nodeCityBlock : jsonNode.get("city_blocks")){
                    cityBlocks.add(JsonManipulator.cityBlockCollect(nodeCityBlock));
                }
                return cityBlocks;
            } catch (IOException | NullPointerException e){
                return new ArrayList<>();
            }
        } else {
            return cityBlocks;
        }
    }

    private static CityDataBlock cityBlockCollect(JsonNode blockJson){
        String teamBlock = blockJson.get("team_name").asText();
        String nameCity = blockJson.get("city_name").asText();
        UUID textUUID = JsonManipulator.getUUIDofField(blockJson);
        int x = blockJson.get("x").asInt();
        int y = blockJson.get("y").asInt();
        int z = blockJson.get("z").asInt();
        if(MinecraftData.warmine.getTeams().isEmpty()){
            return new CityDataBlock(new Coordinate(x,y,z),nameCity,textUUID);
        } else {
            return new CityDataBlock(new Coordinate(x,y,z),nameCity,WarMineData.getWarTeam(teamBlock),textUUID);
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
        UUID textUUID = JsonManipulator.getUUIDofField(blockJson);
        int x = blockJson.get("x").asInt();
        int y = blockJson.get("y").asInt();
        int z = blockJson.get("z").asInt();
        if(MinecraftData.warmine.getTeams().isEmpty()){
            return new CrownDataBlock(new Coordinate(x,y,z),nameCapital,textUUID);
        } else {
            return new CrownDataBlock(new Coordinate(x,y,z),nameCapital,WarMineData.getWarTeam(teamBlock),textUUID);
        }
    }

    public static void saveTeams(List<WarTeam> warTeams){
        ObjectNode rootNode = mapperJson.createObjectNode();
        ArrayNode teamsNode = mapperJson.createArrayNode();
        ArrayNode teamsInWarNode;
        ObjectNode auxiliarNode;

        for(WarTeam warTeam : warTeams){
            auxiliarNode = mapperJson.createObjectNode();
            auxiliarNode.put("name", warTeam.getName());
            auxiliarNode.put("score", warTeam.getScore());

            teamsInWarNode = mapperJson.createArrayNode();
            for(String inWarTeam : warTeam.getTeamsInWar()){
                teamsInWarNode.add(inWarTeam);
            }

            auxiliarNode.set("warteams_name",teamsInWarNode);
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
            auxiliarNode.put("team_name",(player.getWarTeam()!=null?player.getWarTeam().getName():"NullTeam"));
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
            auxiliarNode.put("uuid_entity",crownBlock.getNameUUID()!=null?crownBlock.getNameUUID().toString():"NoText");
            auxiliarNode.put("x",crownBlock.getCoordinate().getX());
            auxiliarNode.put("y",crownBlock.getCoordinate().getY());
            auxiliarNode.put("z",crownBlock.getCoordinate().getZ());
            blocksNode.add(auxiliarNode);
        }

        rootNode.set("crown_blocks",blocksNode);
        JsonManipulator.writeJson("crown_block.json",rootNode);

    }

    public static void saveCityBlocks(List<CityDataBlock> cityBlocks){
        ObjectNode rootNode = mapperJson.createObjectNode();
        ArrayNode blocksNode = mapperJson.createArrayNode();
        ObjectNode auxiliarNode;

        for(CityDataBlock cityBlock : cityBlocks){
            auxiliarNode = mapperJson.createObjectNode();
            auxiliarNode.put("team_name",cityBlock.getWarTeam()!=null?cityBlock.getWarTeam().getName():"NullTeam");
            auxiliarNode.put("city_name",cityBlock.getName());
            auxiliarNode.put("uuid_entity",cityBlock.getNameUUID()!=null?cityBlock.getNameUUID().toString():"NoText");
            auxiliarNode.put("x",cityBlock.getCoordinate().getX());
            auxiliarNode.put("y",cityBlock.getCoordinate().getY());
            auxiliarNode.put("z",cityBlock.getCoordinate().getZ());
            blocksNode.add(auxiliarNode);
        }

        rootNode.set("city_blocks",blocksNode);
        JsonManipulator.writeJson("city_block.json",rootNode);
    }

    private static void writeJson(String jsonName,ObjectNode rootNode){
        try{
            mapperJson.writeValue(FileManipuler.warmineJSONFile(jsonName),rootNode);;
        }catch (IOException e){
            System.out.println("Error Log: "+e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private static UUID getUUIDofField(JsonNode blockJson){
        String keyUUID = "NoText";
        if(blockJson.has("uuid_entity")) {
            keyUUID = blockJson.get("uuid_entity").asText();
        }
        UUID textUUID;
        if(!keyUUID.equalsIgnoreCase("NoText")){
            textUUID = UUID.fromString(keyUUID);
        } else {
            textUUID = null;
        }
        return textUUID;
    }
}
