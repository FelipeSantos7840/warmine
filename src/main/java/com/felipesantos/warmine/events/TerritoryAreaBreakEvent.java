package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.AbstractCityBlock;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class TerritoryAreaBreakEvent {
    @SubscribeEvent
    public static void onTerritoryAreaBreak(BlockEvent.BreakEvent event){
        if(!event.getWorld().isRemote()){
            AbstractCityBlock territoryBlock = WarMineData.getTerritoryBlockInArea(event.getPos());
            if(territoryBlock != null){
                Player player = WarMineData.getPlayer(event.getPlayer().getName().getString());
                if(player.getWarTeam() == null){
                    event.setCanceled(true);
                } else if(!player.getWarTeam().equals(territoryBlock.getWarTeam())){
                    if(!(player.getWarTeam().getTeamsInWar().contains(territoryBlock.getWarTeam().getName()))){
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
