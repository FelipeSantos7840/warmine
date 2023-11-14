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
            boolean validate = player.getWarTeam().getTeamsInWar().remove(teamToStopWar);
            if(validate){
                WarMineData.getWarTeam(teamToStopWar).getTeamsInWar().remove(player.getWarTeam().getName());
                source.sendFeedback(new StringTextComponent(player.getWarTeam().getName()+" retired of war with "+ teamToStopWar+"!"),true);
                return 1;
            } else {
                source.sendFeedback(new StringTextComponent(teamToStopWar+" is not localized!"),true);
            }
        } else {
            source.sendFeedback(new StringTextComponent("You need a team firts!"),true);
        }
        return -1;
    }
}
