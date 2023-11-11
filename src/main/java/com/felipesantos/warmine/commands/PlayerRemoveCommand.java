package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.util.MinecraftTeamsManipulator;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class PlayerRemoveCommand {
    public PlayerRemoveCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("removePlayer")
                        .then(Commands.argument("playername",StringArgumentType.string())
                                .executes((command) ->{
                                    return removeTeamPlayer(command.getSource(),
                                            StringArgumentType.getString(command, "playername"));
                                }))));
    }

    private int removeTeamPlayer(CommandSource source, String playerName){
        boolean val;
        Player player = null;
        for(Player tmpPlayer : MinecraftData.warmine.getPlayers()){
            if(tmpPlayer.getName().equalsIgnoreCase(playerName)){
                player = tmpPlayer;
            }
        }
        if(player != null){
            val = MinecraftTeamsManipulator.removePlayerTeam(player);
            if(val){
                source.sendFeedback(new StringTextComponent(playerName+" removed of the Team!"),true);
            } else {
                source.sendFeedback(new StringTextComponent(playerName+" not have a Team!"),true);
            }
            return 1;
        } else {
            source.sendFeedback(new StringTextComponent(playerName+" not Localized!"),true);
            return -1;
        }
    }
}
