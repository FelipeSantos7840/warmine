package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.MinecraftData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerAddCommand {
    public PlayerAddCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("addPlayer")
                        .then(Commands.argument("nameTeam", StringArgumentType.string())
                                .then(Commands.argument("playerName",StringArgumentType.string()).executes((command) -> {
                                    return 0;
                                })))));
    }

    private int addPlayer(CommandSource source,String nameTeam,String namePlayer) {
        return 1;
    }
}
