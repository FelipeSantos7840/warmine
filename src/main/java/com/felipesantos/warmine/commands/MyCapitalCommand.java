package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.AbstractCityBlock;
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

public class MyCapitalCommand {
    public MyCapitalCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("myCapital").executes((command) -> {
            return myCapital(command.getSource());
        }));
    }

    public int myCapital(CommandSource source) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        WarTeam playerWarTeam = player.getWarTeam();

        if(playerWarTeam != null){
            AbstractCityBlock territoryBlock = WarMineData.getCapital(playerWarTeam);
            if (territoryBlock != null){
                source.sendFeedback(new StringTextComponent(buildCapitalData(playerWarTeam,territoryBlock)),true);
            } else {
                source.sendFeedback(new TranslationTextComponent("command.mycapital.failed1"),true);
            }
        } else {
            source.sendFeedback(new TranslationTextComponent("command.teamsinwar.failed2"),true);
        }
        return -1;
    }

    private String buildCapitalData(WarTeam warTeam, AbstractCityBlock territoryBlock){
        StringBuilder strBuilder = new StringBuilder("--- "+new TranslationTextComponent("block.warmine.crown_block").getString() +" ---\n");

        strBuilder.append(new TranslationTextComponent("command.mycapital.success2").getString()+":" + territoryBlock.getName()+"\n");

        strBuilder.append(new TranslationTextComponent("command.mycapital.success3")
                .getString()+": \u00A7bX\u00A7r: "+territoryBlock.getCoordinate().getX() +
                " \u00A7bY\u00A7r: "+territoryBlock.getCoordinate().getY() +
                " \u00A7bZ\u00A7r: "+territoryBlock.getCoordinate().getZ()+"\n");

        strBuilder.append(new TranslationTextComponent("command.leaderboard.score").getString()+":" + warTeam.getScore()+"\n");

        return strBuilder.toString();
    }
}
