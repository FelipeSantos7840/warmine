package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.block.custom.CrownBlock;
import com.felipesantos.warmine.entities.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;
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
                CrownDataBlock crownBlock = WarMineData.getCapital(event.getPos());
                if(player.getWarTeam() == null){
                    event.setCanceled(true);
                } else if(player.getWarTeam().equals(crownBlock.getWarTeam())){
                    deleteNameOfBlock((ServerWorld) playerEntity.getEntityWorld(),crownBlock);
                    MinecraftData.warmine.getCapitals().remove(crownBlock);
                } else {
                    boolean validate = player.getWarTeam().getTeamsInWar().contains(crownBlock.getWarTeam().getName());
                    if(validate){
                        deleteNameOfBlock((ServerWorld) playerEntity.getEntityWorld(),crownBlock);
                        MinecraftData.warmine.getCapitals().remove(crownBlock);
                    } else {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    public static void deleteNameOfBlock(ServerWorld serverWorld, AbstractCityBlock abstractCityBlock){
        Entity entity = serverWorld.getEntityByUuid(abstractCityBlock.getNameUUID());
        if(entity != null){
            serverWorld.removeEntity(entity,true);
        }
    }
}
