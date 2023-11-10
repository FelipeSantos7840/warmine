package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.util.FileManipuler;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class WorldSaveEvent {
    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save event){
        if(WarMine.IS_POSSIBLE){
            FileManipuler.warmineSaveData(MinecraftData.warmine);
        }
    }
}