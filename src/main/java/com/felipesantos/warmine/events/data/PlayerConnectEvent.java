package com.felipesantos.warmine.events.data;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.util.FileManipuler;
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
            if(!event.getPlayer().getEntityWorld().isRemote()){
                String path;
                boolean pathLocalized = FileManipuler.locateWorldSave(event.getPlayer().getServer());
                if(pathLocalized){
                    WarMine.IS_POSSIBLE = true;
                    event.getPlayer().sendStatusMessage(new StringTextComponent("World File localizated!"),true);
                } else {
                    event.getPlayer().sendStatusMessage(new StringTextComponent("World File not found!"),true);
                }
                isInitialConnect = true;
            }
        }
    }
}
