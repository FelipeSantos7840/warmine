package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.util.FileManipuler;
import com.felipesantos.warmine.util.MinecraftTeamsManipulator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class PlayerConnectEvent {

    @SubscribeEvent
    public static void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event){
        PlayerEntity player = event.getPlayer();
        String playerName = player.getName().getString();
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;

        if(compareWorldName(player.getServer())){
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

                event.getPlayer().sendStatusMessage(new TranslationTextComponent("event.firstconnect.success"),true);
            } else {
                event.getPlayer().sendStatusMessage(new TranslationTextComponent("event.firstconnect.failed"),true);
            }
        }
        if(MinecraftData.warmine.playerDataExist(playerName) == null){
            MinecraftData.warmine.getPlayers().add(MinecraftTeamsManipulator.addPlayerTeam(null,new Player(playerName)));
        }

        System.out.println("LOG | Player Connect: " + player.getName().getString() + " | " + serverPlayerEntity.getPlayerIP());
        sendTitleToPlayer(serverPlayerEntity,"event.playerconnect.success");
    }

    public static void sendTitleToPlayer(ServerPlayerEntity serverPlayerEntity, String translationID){
        serverPlayerEntity.connection.sendPacket(new STitlePacket(STitlePacket.Type.TITLE,new TranslationTextComponent(translationID),1,3,1));
    }

    private static boolean compareWorldName(MinecraftServer server){
        return FileManipuler.getWorldName() == null || !(server.getServerConfiguration().getWorldName().equalsIgnoreCase(FileManipuler.getWorldName()));
    }
}
