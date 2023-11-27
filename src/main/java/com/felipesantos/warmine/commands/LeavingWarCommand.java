package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.entities.WarTeam;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class LeavingWarCommand {
    public LeavingWarCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("leavingWar")
                        .then(Commands.argument("teamToStopWar", StringArgumentType.string())
                                .executes((commands) ->{
                                    return leavingWar(commands.getSource(),StringArgumentType.getString(commands,"teamToStopWar"));
                                }))));
    }

    private int leavingWar(CommandSource source,String teamToStopWar){
        PlayerEntity playerEntity = (PlayerEntity) source.getEntity();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());

        if(player.getWarTeam() != null){
            if(player.getWarTeam().getScore() >= 15) {
                boolean validate = player.getWarTeam().getTeamsInWar().remove(teamToStopWar);
                if (validate) {
                    WarMineData.getWarTeam(teamToStopWar).getTeamsInWar().remove(player.getWarTeam().getName());
                    player.getWarTeam().decrementScore(15);
                    source.sendFeedback(new StringTextComponent(player.getWarTeam().getName())
                            .appendSibling(new TranslationTextComponent("command.leavingwar.success")
                                    .appendSibling(new StringTextComponent(teamToStopWar)
                                            .appendSibling(new StringTextComponent("!")))), true);
                    return 1;
                } else {
                    source.sendFeedback(new StringTextComponent(teamToStopWar).appendSibling(new TranslationTextComponent("command.leavingwar.failed1")), true);
                }
            } else {
                source.sendFeedback(new TranslationTextComponent("command.leavingwar.failed2"),true);
            }
        } else {
            source.sendFeedback(new TranslationTextComponent("command.leavingwar.failed3"),true);
        }
        return -1;
    }
}
