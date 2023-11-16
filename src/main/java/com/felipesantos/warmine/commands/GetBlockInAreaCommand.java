package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
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
                    source.sendFeedback(
                            new StringTextComponent("The Territory Block Position: X:" + coordinate.getX()
                                    + ", Y: " + coordinate.getY()
                                    + ", Z: " + coordinate.getZ()), true);
                } else {
                    source.sendFeedback(new StringTextComponent("It is not a conquered territory"), true);
                }
            } else {
                source.sendFeedback(new StringTextComponent("You need to be in the Overworld to get Block Position!"),true);
            }
        }
        return -1;
    }
}
