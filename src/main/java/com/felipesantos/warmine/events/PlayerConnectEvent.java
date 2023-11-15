package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.util.FileManipuler;
import com.felipesantos.warmine.util.MinecraftTeamsManipulator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class PlayerConnectEvent {

    private static boolean isInitialConnect = false;

    @SubscribeEvent
    public static void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event){
        PlayerEntity player = event.getPlayer();
        if(!isInitialConnect){
            String path;
            boolean pathLocalized = FileManipuler.locateWorldSave(player.getServer());
            if(pathLocalized){
                WarMine.IS_POSSIBLE = true;

                MinecraftData.server = player.getServer();
                MinecraftData.world = player.getEntityWorld();
                MinecraftData.score = player.getWorldScoreboard();
                MinecraftData.warmine = new WarMineData();

                if(FileManipuler.warmineFolderExist()){
                    FileManipuler.warmineCollectData(MinecraftData.warmine);
                }

                event.getPlayer().sendStatusMessage(new StringTextComponent("Welcome! Warmine is working"),true);
            } else {
                event.getPlayer().sendStatusMessage(new StringTextComponent("Welcome! Failed locate World!"),true);
            }
            isInitialConnect = true;
        }

        String playerName = player.getName().getString();
        if(MinecraftData.warmine.playerDataExist(playerName) == null){
            MinecraftData.warmine.getPlayers().add(MinecraftTeamsManipulator.addPlayerTeam(null,new Player(playerName)));
        }
    }
}
