package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class TerritoryAreaPlaceEvent {

    @SubscribeEvent
    public static void onTerritoryAreaPlace(BlockEvent.EntityPlaceEvent event){
        if(!event.getWorld().isRemote()){
            if(event.getEntity() instanceof PlayerEntity){

                if(World.OVERWORLD == event.getEntity().world.getDimensionKey()) {
                    AbstractCityBlock territoryBlock = WarMineData.getTerritoryBlockInArea(event.getPos());
                    if (territoryBlock != null) {
                        PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
                        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
                        if (player.getWarTeam() == null) {
                            MinecraftData.world.destroyBlock(event.getPos(), true);
                        } else if (!player.getWarTeam().equals(territoryBlock.getWarTeam())) {
                            if (player.getWarTeam().getTeamsInWar().contains(territoryBlock.getWarTeam().getName())) {
                                BlockState block = event.getState();
                                if (!(block.getBlock() == Blocks.TNT || block.getBlock() == Blocks.LADDER || block.getBlock() == Blocks.SCAFFOLDING)) {
                                    MinecraftData.world.destroyBlock(event.getPos(), true);
                                }
                            } else {
                                MinecraftData.world.destroyBlock(event.getPos(), true);
                            }
                        }
                    }
                }
            }
        }
    }
}
