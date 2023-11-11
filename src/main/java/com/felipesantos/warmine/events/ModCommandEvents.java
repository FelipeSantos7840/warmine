package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.commands.PlayerAddCommand;
import com.felipesantos.warmine.commands.PlayerRemoveCommand;
import com.felipesantos.warmine.commands.TeamCreateCommand;
import com.felipesantos.warmine.commands.TeamRemoveCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class ModCommandEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new TeamCreateCommand(event.getDispatcher());
        new TeamRemoveCommand(event.getDispatcher());
        new PlayerAddCommand(event.getDispatcher());
        new PlayerRemoveCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
