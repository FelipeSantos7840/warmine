package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class DeclareWarCommand {
    public DeclareWarCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("declareWar")
                        .then(Commands.argument("teamToWar", StringArgumentType.string())
                                .executes((command) -> {
                                    return declareWar(command.getSource(),StringArgumentType.getString(command, "teamToWar"));
                                }))));
    }

    private int declareWar(CommandSource source,String teamToWar) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        if(player.getWarTeam() != null){
            AbstractCityBlock territoryDataBlock = WarMineData.getTerritoryBlockInArea(playerEntity.getPosition());
            if(territoryDataBlock != null){
                WarTeam teamWar = territoryDataBlock.getWarTeam();
                if(!teamWar.equals(player.getWarTeam())) {
                    if (teamWar.getName().equalsIgnoreCase(teamToWar) && !(teamsAlreadyInWar(territoryDataBlock.getWarTeam(), player.getWarTeam().getName()))) {
                        if(player.getWarTeam().getScore() >= 30){
                            player.getWarTeam().decrementScore(30);
                            territoryDataBlock.getWarTeam().addTeamInWar(player.getWarTeam().getName());
                            player.getWarTeam().addTeamInWar(teamWar.getName());
                            source.sendFeedback(new StringTextComponent(player.getWarTeam().getName() + " declared War with " + teamWar.getName()), true);
                            return 1;
                        } else {
                            source.sendFeedback(new StringTextComponent("Declare war cost 30 points!!"),true);
                        }
                    } else {
                        source.sendFeedback(new StringTextComponent("Teams already in war or territory is not the same of solicited!"), true);
                    }
                } else {
                    source.sendFeedback(new StringTextComponent("You can't declare war to your own team!"),true);
                }
            } else {
                source.sendFeedback(new StringTextComponent("Not in enemy territory!"),true);
            }
        } else {
            source.sendFeedback(new StringTextComponent("You need a team to declare war!"),true);
        }
        return -1;
    }

    public boolean teamsAlreadyInWar(WarTeam baseTeam, String requireWarTeam){
        return baseTeam.getTeamsInWar().contains(requireWarTeam);
    }
}
