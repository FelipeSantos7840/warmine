package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.commands.*;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class ModCommandEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new DeclareWarCommand(event.getDispatcher());
        new LeavingWarCommand(event.getDispatcher());
        new TeamCreateCommand(event.getDispatcher());
        new TeamRemoveCommand(event.getDispatcher());
        new PlayerAddCommand(event.getDispatcher());
        new PlayerRemoveCommand(event.getDispatcher());
        new ToCapitalCommand(event.getDispatcher());
        new GetBlockInAreaCommand(event.getDispatcher());
        new TerritoryCommand(event.getDispatcher());
        new NameTerritoryCommand(event.getDispatcher());
        new LeaderboardCommand(event.getDispatcher());
        new GetPointsCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
