package com.felipesantos.warmine.commands;


import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TranslationTextComponent;

import java.io.File;
import java.io.IOException;

public class StartWarMineCommand {
    public StartWarMineCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("start").then(Commands.literal("warmine").executes((commands) -> {
            return startWarmine(commands.getSource());
        })));
    }

    private static int startWarmine(CommandSource source){
        if(!source.getWorld().isRemote()){
            String path = getSavePath(source.getServer(),"warmine\\initial.json");
            if(createWarmineConfigFile(path)){

                source.sendFeedback(new TranslationTextComponent("commands.warmine.startwarmine.success"), true);
                return 1;
            } else{
                source.sendFeedback(new TranslationTextComponent("commands.warmine.startwarmine.fail2"), true);
                return -1;
            }

        }
        source.sendFeedback(new TranslationTextComponent("commands.warmine.startwarmine.fail1"), true);
        return -1;
    }

    public static String getSavePath(MinecraftServer server, String pathFile){
        String path = server.getServerConfiguration().getWorldName();
        return ".\\saves\\" + path.substring(1) +"\\"+ pathFile;
    }

    public static boolean existsPath(String path){
        return new File(path).exists();
    }

    private static boolean createWarmineConfigFile(String path){
        File file = new File(path);
        try{
           File parentFile =file.getParentFile();
           if(!parentFile.exists()){
                parentFile.mkdirs();
           }
           if(file.createNewFile()){
               return true;
           } else {
               return false;
           }
        } catch (IOException | SecurityException e){
            System.out.println("Erro: Criação do Arquivo Falhou!");
        }
        return false;
    }
}
