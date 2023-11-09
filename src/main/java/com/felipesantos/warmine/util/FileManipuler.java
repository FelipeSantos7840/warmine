package com.felipesantos.warmine.util;

import com.felipesantos.warmine.WarMine;
import net.minecraft.server.MinecraftServer;

import java.io.File;

public class FileManipuler {

    private static String worldSavePath;
    private static String worldName;
    public static boolean locateWorldSave(MinecraftServer server){
        if(server == null){
            return false;
        }

        setWorldName(server);
        String worldPath = searchNormalPath(".\\saves");
        if (worldPath != null) {
            setWorldSavePath(worldPath);
            return true;
        } else {
            worldPath = searchNormalPath(".");
            if(worldPath != null){
                setWorldSavePath(worldPath);
                return true;
            }
        }
        return false;
    }

    private static String searchNormalPath(String pathSearch){
        File file = new File(pathSearch+"\\"+worldName);
        if(file.exists()){
            System.out.println("Log: WORLD_FOUND:"+file.getPath());
            return file.getPath();
        } else {
            return null;
        }
    }

    public static boolean warmineFolderExist(){
        if(!WarMine.IS_POSSIBLE){
            return false;
        }

        File warmineFolder = new File(FileManipuler.worldSavePath + "\\warmine");
        if(!warmineFolder.exists()){
            return warmineFolder.mkdirs();
        } else {
            return true;
        }
    }

    private static void setWorldName(MinecraftServer server){
        worldName = server.getServerConfiguration().getWorldName();
    }

    public static void setWorldSavePath(String worldSavePath) {
        FileManipuler.worldSavePath = worldSavePath;
    }

    public static String getWorldSavePath() {
        return worldSavePath;
    }

    public static String getWorldName() {
        return worldName;
    }
}
