package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class DeclareWarCommand {

    private static final String BASE_ERROR = "command.declarewar.failed";

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
        if(WarMineData.DAY_OF_WAR) {
            if (player.getWarTeam() != null) {
                if (World.OVERWORLD == playerEntity.world.getDimensionKey()) {
                    AbstractCityBlock territoryDataBlock = WarMineData.getTerritoryBlockInArea(playerEntity.getPosition());
                    if (territoryDataBlock != null) {
                        WarTeam teamWar = territoryDataBlock.getWarTeam();
                        if (!teamWar.equals(player.getWarTeam())) {
                            if (teamWar.getName().equalsIgnoreCase(teamToWar) && !(teamsAlreadyInWar(territoryDataBlock.getWarTeam(), player.getWarTeam().getName()))) {
                                if (player.getWarTeam().getScore() >= 30) {
                                    player.getWarTeam().decrementScore(30);
                                    territoryDataBlock.getWarTeam().addTeamInWar(player.getWarTeam().getName());
                                    player.getWarTeam().addTeamInWar(teamWar.getName());
                                    source.sendFeedback(new StringTextComponent(player.getWarTeam().getName())
                                            .appendSibling(new TranslationTextComponent("command.declarewar.success")
                                                    .appendSibling(new StringTextComponent(teamWar.getName()))), true);
                                    return 1;
                                } else {
                                    source.sendFeedback(new TranslationTextComponent(BASE_ERROR+"1"), true);
                                }
                            } else {
                                source.sendFeedback(new TranslationTextComponent(BASE_ERROR+"2"), true);
                            }
                        } else {
                            source.sendFeedback(new TranslationTextComponent(BASE_ERROR+"3"), true);
                        }
                    } else {
                        source.sendFeedback(new TranslationTextComponent(BASE_ERROR+"4"), true);
                    }
                } else {
                    source.sendFeedback(new TranslationTextComponent(BASE_ERROR+"5"), true);
                }
            } else {
                source.sendFeedback(new TranslationTextComponent(BASE_ERROR+"6"), true);
            }
        } else {
            source.sendFeedback(new TranslationTextComponent(BASE_ERROR+"7"), true);
        }
        return -1;
    }

    public boolean teamsAlreadyInWar(WarTeam baseTeam, String requireWarTeam){
        return baseTeam.getTeamsInWar().contains(requireWarTeam);
    }
}
