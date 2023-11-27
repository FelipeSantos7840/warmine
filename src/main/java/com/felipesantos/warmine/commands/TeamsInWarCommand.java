package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.entities.WarTeam;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TeamsInWarCommand {
    public TeamsInWarCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("teamsInWar").executes((command) -> {

            return teamsInWar(command.getSource());

        })));
    }

    private int teamsInWar(CommandSource source) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        if (player != null && player.getWarTeam() != null) {
            WarTeam playerTeam = player.getWarTeam();
            if(!playerTeam.getTeamsInWar().isEmpty()){
                StringBuilder strBuilder = new StringBuilder("----"+playerTeam.getName()+new TranslationTextComponent("command.teamsinwar.success").getString()+"----\n");
                for(String teamInWarName : playerTeam.getTeamsInWar()){
                    strBuilder.append("[\u00A7b"+teamInWarName+"\u00A7r]");
                }
                source.sendFeedback(new StringTextComponent(strBuilder.toString()),true);
                return 1;
            } else {
                source.sendFeedback(new StringTextComponent(playerTeam.getName())
                        .appendSibling(new TranslationTextComponent("command.teamsinwar.failed1")),true);
            }
        } else {
            source.sendFeedback(new TranslationTextComponent("command.teamsinwar.failed2"),true);
        }
        return -1;
    }
}
