package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.util.MinecraftTeamsManipulator;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TeamRemoveCommand {
    public TeamRemoveCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("removeTeam")
                        .requires(source -> source.hasPermissionLevel(4))
                        .then(Commands.argument("nameteam", StringArgumentType.string()).executes((command)-> {

            return teamRemove(command.getSource(),StringArgumentType.getString(command, "nameteam"));

        }))));
    }

    private int teamRemove(CommandSource source,String teamName) throws CommandSyntaxException {
            MinecraftTeamsManipulator.removeDataTeam(teamName);
            source.sendFeedback(new TranslationTextComponent("command.teamremove.success"),true);
            return 1;
    }
}
