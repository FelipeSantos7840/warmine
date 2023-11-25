package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.CrownDataBlock;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.WarMineData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class CrownTimerEvent {

    private static int ticksElapsed = 0;
    private static final int TICKS_PER_MINUTE = 1200;

    @SubscribeEvent
    public static void onCrownTimer(TickEvent.ServerTickEvent event){
        if (event.phase == TickEvent.Phase.START) {
            ticksElapsed++;
            if(ticksElapsed >= 10*TICKS_PER_MINUTE){
                if(WarMine.IS_POSSIBLE) {
                    incrementPoints();
                }
                ticksElapsed = 0;
            }
        }
    }

    private static void incrementPoints(){
        System.out.println("LOG | INCREMENT CAPITALS");
        if(MinecraftData.warmine.getCapitals() != null){
            for(CrownDataBlock crownBlock : MinecraftData.warmine.getCapitals()){
                if(crownBlock.getWarTeam() != null){
                    crownBlock.getWarTeam().incrementScore(1);
                }
            }
        }

    }
}
