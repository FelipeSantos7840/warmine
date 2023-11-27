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

public class PlayerAddCommand {
    public PlayerAddCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("addPlayer")
                        .requires(source -> source.hasPermissionLevel(4))
                            .then(Commands.argument("nameteam", StringArgumentType.string())
                                    .then(Commands.argument("playername",StringArgumentType.string()).executes((command) -> {
                                        return addPlayer(command.getSource(),
                                                StringArgumentType.getString(command, "nameteam"),
                                                StringArgumentType.getString(command, "playername"));
                                })))));
    }

    private int addPlayer(CommandSource source,String nameTeam,String namePlayer) throws CommandSyntaxException {
        Player player = MinecraftData.warmine.playerDataExist(namePlayer);
        if(player != null){
            if(player.getWarTeam() == null){
                MinecraftTeamsManipulator.addPlayerTeam(nameTeam,player);
                if(player.getWarTeam() != null){
                    source.sendFeedback(new StringTextComponent(namePlayer)
                            .appendSibling(new TranslationTextComponent("command.playeradd.success")
                                    .appendSibling(new StringTextComponent(nameTeam+"!"))),true);
                } else {
                    source.sendFeedback(new StringTextComponent(nameTeam)
                            .appendSibling(new TranslationTextComponent("command.playeradd.failed1")),true);
                }

                return 1;
            } else {
                source.sendFeedback(new StringTextComponent(namePlayer)
                        .appendSibling(new TranslationTextComponent("command.playeradd.failed2")),true);
            }
        } else {
            source.sendFeedback(new TranslationTextComponent("command.playeradd.failed3"),true);
        }
        return -1;
    }
}
