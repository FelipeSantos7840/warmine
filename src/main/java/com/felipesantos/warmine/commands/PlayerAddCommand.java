package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.util.MinecraftTeamsManipulator;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class PlayerAddCommand {
    public PlayerAddCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("addPlayer")
                        .then(Commands.argument("nameteam", StringArgumentType.string())
                                .then(Commands.argument("playername",StringArgumentType.string()).executes((command) -> {
                                    return addPlayer(command.getSource(),
                                            StringArgumentType.getString(command, "nameteam"),
                                            StringArgumentType.getString(command, "playername"));
                                })))));
    }

    private int addPlayer(CommandSource source,String nameTeam,String namePlayer) {
        Player player = MinecraftData.warmine.playerDataExist(namePlayer);
        if(player != null){
            if(player.getTeam() == null){
                MinecraftTeamsManipulator.addPlayerTeam(nameTeam,player);
                if(player.getTeam() != null){
                    source.sendFeedback(new StringTextComponent(namePlayer+" added to "+nameTeam+"!"),true);
                } else {
                    source.sendFeedback(new StringTextComponent(nameTeam+" not found!"),true);
                }

                return 1;
            } else {
                source.sendFeedback(new StringTextComponent(namePlayer+" already on a Team!"),true);
            }
        } else {

            source.sendFeedback(new StringTextComponent("Error to Locate User!"),true);
        }
        return -1;
    }
}
