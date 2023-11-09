package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.util.FileManipuler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class PlayerConnectEvent {

    public static boolean isInitialConnect = false;

    @SubscribeEvent
    public static void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event){
        if(!isInitialConnect){
            PlayerEntity player = event.getPlayer();
            String path;
            boolean pathLocalized = FileManipuler.locateWorldSave(player.getServer());
            if(pathLocalized){
                WarMine.IS_POSSIBLE = true;

                MinecraftData.server = player.getServer();
                MinecraftData.world = player.getEntityWorld();
                MinecraftData.score = player.getWorldScoreboard();

                //Commented below line is a test
                //boolean created = FileManipuler.warmineFolderExist();

                event.getPlayer().sendStatusMessage(new StringTextComponent("World File localizated!"),true);
            } else {
                event.getPlayer().sendStatusMessage(new StringTextComponent("World File not found!"),true);
            }
            isInitialConnect = true;
        }
    }
}
