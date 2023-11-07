package com.felipesantos.warmine.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class WarmineJson {
    public static void initialJsonConfig(String path,String worldName){
        File fileDirectory = new File(path);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonData = mapper.createObjectNode();

        jsonData.put("worldname",worldName);
    }
}
