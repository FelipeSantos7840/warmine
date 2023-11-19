package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.WarTeam;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

import java.util.List;

public class RemoveAllWarsCommand {
    public RemoveAllWarsCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("removeAllWars").requires(source -> source.hasPermissionLevel(4)).executes((command) -> {
            return removeAllWars(command.getSource());
        }));
    }

    private int removeAllWars(CommandSource source){
        List<WarTeam> warTeams = MinecraftData.warmine.getTeams();
        for(WarTeam warTeam : warTeams){
            if(!(warTeam.getTeamsInWar().isEmpty())){
                warTeam.getTeamsInWar().clear();
            }
        }
        return 1;
    }
}
