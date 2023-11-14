package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.block.custom.CityBlock;
import com.felipesantos.warmine.block.custom.CrownBlock;
import com.felipesantos.warmine.entities.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class CityBreakEvent {

    @SubscribeEvent
    public static void onCityBreak(BlockEvent.BreakEvent event){
        if(!event.getWorld().isRemote()){
            if(event.getState().getBlock() instanceof CityBlock){
                PlayerEntity playerEntity = event.getPlayer();
                Player player = WarMineData.getPlayer(playerEntity.getName().getString());
                CityDataBlock cityBlock = WarMineData.getCity(event.getPos());
                if(player.getWarTeam() == null){
                    event.setCanceled(true);
                } else if(player.getWarTeam().equals(cityBlock.getWarTeam())){
                    MinecraftData.warmine.getCities().remove(cityBlock);
                } else {
                    boolean validate = player.getWarTeam().getTeamsInWar().contains(cityBlock.getWarTeam().getName());
                    if(validate){
                        MinecraftData.warmine.getCities().remove(cityBlock);
                    } else {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
