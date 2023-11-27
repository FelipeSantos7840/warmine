package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.util.MinecraftTeamsManipulator;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PlayerRemoveCommand {
    public PlayerRemoveCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("removePlayer")
                        .requires(source -> source.hasPermissionLevel(4))
                            .then(Commands.argument("playername",StringArgumentType.string())
                                    .executes((command) ->{
                                        return removeTeamPlayer(command.getSource(),
                                                StringArgumentType.getString(command, "playername"));
                                    }))));
    }

    private int removeTeamPlayer(CommandSource source, String playerName) throws CommandSyntaxException {
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
                source.sendFeedback(new StringTextComponent(playerName)
                        .appendSibling(new TranslationTextComponent("command.playerremove.success")),true);
            } else {
                source.sendFeedback(new StringTextComponent(playerName)
                        .appendSibling(new TranslationTextComponent("command.playerremove.failed1")),true);
            }
            return 1;
        } else {
            source.sendFeedback(new StringTextComponent(playerName)
                    .appendSibling(new TranslationTextComponent("command.playerremove.failed2")),true);
            return -1;
        }
    }
}
