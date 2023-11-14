package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.CrownDataBlock;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class CrownAreaBreakEvent {
    @SubscribeEvent
    public static void onCrownAreaBreak(BlockEvent.BreakEvent event){
        if(!event.getWorld().isRemote()){
            CrownDataBlock crownBlock = WarMineData.getCapitalInArea(event.getPos());
            if(crownBlock != null){
                Player player = WarMineData.getPlayer(event.getPlayer().getName().getString());
                if(player.getWarTeam() == null){
                    event.setCanceled(true);
                } else if(!player.getWarTeam().equals(crownBlock.getWarTeam())){
                    if(!(player.getWarTeam().getTeamsInWar().contains(crownBlock.getWarTeam().getName()))){
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}