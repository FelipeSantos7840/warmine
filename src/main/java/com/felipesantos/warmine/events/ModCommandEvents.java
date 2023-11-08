package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class ModCommandEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {


        ConfigCommand.register(event.getDispatcher());
    }
}
