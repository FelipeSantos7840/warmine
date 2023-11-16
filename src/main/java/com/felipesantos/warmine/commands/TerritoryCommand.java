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
import net.minecraft.world.World;

public class TerritoryCommand {
    public TerritoryCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("isTerritory").executes((command) -> {
            return isTerritory(command.getSource());
        }));
    }

    private int isTerritory(CommandSource source) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());

        AbstractCityBlock territoryBlock = WarMineData.getTerritoryBlockInArea(playerEntity.getPosition());
        if(World.OVERWORLD == playerEntity.world.getDimensionKey()) {
            if (territoryBlock != null) {
                if (territoryBlock.getWarTeam() != null) {
                    source.sendFeedback(new StringTextComponent(territoryBlock.getWarTeam().getName() + " Territory!!"), true);
                    return 1;
                }
            } else {
                source.sendFeedback(new StringTextComponent("It is not a conquered territory"), true);
            }
        } else {
            source.sendFeedback(new StringTextComponent("isTerritory is available only Overworld"), true);
        }
        return -1;
    }
}
