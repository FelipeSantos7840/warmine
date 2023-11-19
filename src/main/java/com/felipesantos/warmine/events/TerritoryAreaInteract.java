package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.AbstractCityBlock;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class TerritoryAreaInteract {
    @SubscribeEvent
    public static void onTerritoryAreaInteract(PlayerInteractEvent.RightClickBlock event){
        if(!event.getWorld().isRemote()){
            if(World.OVERWORLD == event.getPlayer().world.getDimensionKey()) {
                AbstractCityBlock territoryBlock = WarMineData.getTerritoryBlockInArea(event.getPos());
                BlockState blockState = event.getWorld().getBlockState(event.getPos());
                if (territoryBlock != null) {
                    Player player = WarMineData.getPlayer(event.getPlayer().getName().getString());
                    if (player.getWarTeam() == null) {
                        event.setCanceled(true);
                    } else if (!player.getWarTeam().equals(territoryBlock.getWarTeam())) {
                        if (!(player.getWarTeam().getTeamsInWar().contains(territoryBlock.getWarTeam().getName()))) {
                            if(isDoor(blockState.getBlock() ) || isButton(blockState.getBlock()) || isChest(blockState.getBlock())){
                                event.setCanceled(true);
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isDoor(Block block) {
        return block instanceof DoorBlock;
    }

    private static boolean isButton(Block block) {
        return block instanceof StoneButtonBlock || block instanceof WoodButtonBlock;
    }

    private static boolean isChest(Block block){
        return block instanceof ChestBlock || block instanceof EnderChestBlock;
    }
}
