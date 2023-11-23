package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.AbstractCityBlock;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class TerritoryAreaExplosion {

    @SubscribeEvent
    public static void onTerritoryAreaExplosion(ExplosionEvent.Detonate event){
        Explosion explosion = event.getExplosion();
        if(explosion.getExploder() instanceof CreeperEntity){
            List<Entity> affectedPlayers = event.getAffectedEntities();
            boolean territoryColide = false;
            boolean haveACapitalPlayer = false;
            AbstractCityBlock territoryBlock = null;
            for(BlockPos pos : event.getAffectedBlocks()){
                territoryBlock = WarMineData.getTerritoryBlockInArea(pos);
                if(territoryBlock != null){
                    territoryColide = true;
                    break;
                }
            }
            if(territoryColide) {
                Player warPlayer;
                for (Entity entity : affectedPlayers) {
                    if (entity instanceof PlayerEntity) {
                        warPlayer = WarMineData.getPlayer(entity.getName().getString());
                        if(warPlayer.getWarTeam() != null) {
                            if(warPlayer.getWarTeam().equals(territoryBlock.getWarTeam())){
                                haveACapitalPlayer = true;
                            }
                        }
                    }
                }
            }
            if(territoryColide && !haveACapitalPlayer){
                event.getAffectedBlocks().clear();
            }
        }
    }
}
