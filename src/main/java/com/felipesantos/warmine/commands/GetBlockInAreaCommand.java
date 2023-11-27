package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class GetBlockInAreaCommand {
    public GetBlockInAreaCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("warmine").then(Commands.literal("blockInArea").executes((command)-> {
            return getBlockInArea(command.getSource());
        })));
    }

    private int getBlockInArea(CommandSource source) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        WarTeam playerWarTeam = player.getWarTeam();

        if(playerWarTeam != null){
            if(World.OVERWORLD == playerEntity.world.getDimensionKey()) {
                AbstractCityBlock territoryBlock = WarMineData.getTerritoryBlockInArea(playerEntity.getPosition());
                if (territoryBlock != null) {
                    Coordinate coordinate = territoryBlock.getCoordinate();
                    source.sendFeedback(getFullSuccessText(coordinate),true);
                } else {
                    source.sendFeedback(new TranslationTextComponent("command.getblockinarea.failed1"), true);
                }
            } else {
                source.sendFeedback(new TranslationTextComponent("command.getblockinarea.failed2"),true);
            }
        }
        return -1;
    }

    private IFormattableTextComponent getFullSuccessText(Coordinate coord){
        return new TranslationTextComponent("command.getblockinarea.success")
                .appendSibling(new StringTextComponent(" X: " + coord.getX() + ",Y: " + coord.getY() + ",Z: "+coord.getZ()));
    }
}
