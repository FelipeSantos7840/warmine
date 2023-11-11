package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.entities.WarTeam;
import com.felipesantos.warmine.util.MinecraftTeamsManipulator;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class TeamCreateCommand {
    public TeamCreateCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("addTeam").requires(source -> source.hasPermissionLevel(4))
                        .then(Commands.argument("nameteam", StringArgumentType.string()).executes((command)-> {

            return teamCreate(command.getSource(),
                    StringArgumentType.getString(command, "nameteam"));

        }))));
    }

    private int teamCreate(CommandSource source,String teamName) throws CommandSyntaxException {
        if(WarMineData.getTeam(teamName) == null){
            MinecraftTeamsManipulator.createNewMinecraftTeam(teamName);
            MinecraftData.warmine.getTeams().add(new WarTeam(teamName,WarMineData.INITIAL_SCORE));
            return 1;
        }
        return -1;
    }
}
