package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.block.WarMineBlocks;
import com.felipesantos.warmine.block.custom.CrownBlock;
import com.felipesantos.warmine.entities.CrownDataBlock;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class CrownBreakEvent {

    @SubscribeEvent
    public static void onCrownBreak(BlockEvent.BreakEvent event){
        if(!event.getWorld().isRemote()){
            if(event.getState().getBlock() instanceof CrownBlock){
                PlayerEntity playerEntity = event.getPlayer();
                Player player = WarMineData.getPlayer(playerEntity.getName().getString());
                CrownDataBlock crowBlock = WarMineData.getCapital(event.getPos());
                if(player.getWarTeam() == null || !(player.getWarTeam().equals(crowBlock.getWarTeam()))){
                    event.setCanceled(true);
                } else {
                    MinecraftData.warmine.getCapitals().remove(crowBlock);
                }
            }
        }
    }
}
