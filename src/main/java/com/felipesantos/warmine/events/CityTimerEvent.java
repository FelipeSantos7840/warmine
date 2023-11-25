package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class CityTimerEvent {

    private static int ticksElapsed = 0;
    private static final int TICKS_PER_MINUTE = 1200;

    @SubscribeEvent
    public static void onCityTimer(TickEvent.ServerTickEvent event){
        if (event.phase == TickEvent.Phase.START) {
            ticksElapsed++;
            if(ticksElapsed >= 30*TICKS_PER_MINUTE){
                if(WarMine.IS_POSSIBLE) {
                    incrementPoints();
                }
                ticksElapsed = 0;
            }
        }
    }

    private static void incrementPoints(){
        if(MinecraftData.warmine.getCities() != null){
            for(CityDataBlock cityBlock : MinecraftData.warmine.getCities()){
                if(cityBlock.getWarTeam() != null){
                    if(WarMineData.teamAlreadyHaveACapital(cityBlock.getWarTeam())){
                        cityBlock.getWarTeam().incrementScore(1);
                    }
                }
            }
        }

    }

}
