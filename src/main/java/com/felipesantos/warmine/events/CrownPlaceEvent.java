package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.CrownDataBlock;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class CrownPlaceEvent {

    @SubscribeEvent
    public static void onCrownAreaPlace(BlockEvent.EntityPlaceEvent event){
        if(!event.getWorld().isRemote()){
            if(event.getEntity() instanceof PlayerEntity){
                CrownDataBlock crownBlock = WarMineData.getCapitalInArea(event.getPos());
                if (crownBlock != null) {
                    PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
                    Player player = WarMineData.getPlayer(playerEntity.getName().getString());
                    if (player.getWarTeam() == null || !(player.getWarTeam().equals(crownBlock.getWarTeam()))) {
                        MinecraftData.world.destroyBlock(event.getPos(),true);
                    }
                }
            }
        }
    }
}
