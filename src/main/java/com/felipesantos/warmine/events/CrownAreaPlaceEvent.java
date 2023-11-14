package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.CrownDataBlock;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class CrownAreaPlaceEvent {

    @SubscribeEvent
    public static void onCrownAreaPlace(BlockEvent.EntityPlaceEvent event){
        if(!event.getWorld().isRemote()){
            if(event.getEntity() instanceof PlayerEntity){
                CrownDataBlock crownBlock = WarMineData.getCapitalInArea(event.getPos());
                if (crownBlock != null) {
                    PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
                    Player player = WarMineData.getPlayer(playerEntity.getName().getString());
                    if (player.getWarTeam() == null) {
                        MinecraftData.world.destroyBlock(event.getPos(),true);
                    } else if (!player.getWarTeam().equals(crownBlock.getWarTeam())){
                        if(player.getWarTeam().getTeamsInWar().contains(crownBlock.getWarTeam().getName())){
                            BlockState block= event.getState();
                            if(!(block.getBlock() == Blocks.TNT || block.getBlock() == Blocks.LADDER || block.getBlock() == Blocks.SCAFFOLDING)){
                                MinecraftData.world.destroyBlock(event.getPos(),true);
                            }
                        } else {
                            MinecraftData.world.destroyBlock(event.getPos(),true);
                        }
                    }
                }
            }
        }
    }
}
