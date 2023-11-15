package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ToCapitalCommand {
    public ToCapitalCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("toCapital").executes((command) -> {
            return toCapital(command.getSource());
        }));
    }

    private int toCapital(CommandSource source) throws CommandSyntaxException {
        PlayerEntity playerEntity =source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        WarTeam playerWarTeam = player.getWarTeam();
        if(playerWarTeam != null){
            if(WarMineData.teamAlreadyHaveACapital(playerWarTeam)){

                CrownDataBlock crownDataBlock = WarMineData.getCapital(playerWarTeam);
                Coordinate crownCoordinate = crownDataBlock.getCoordinate();

                playerEntity.setPositionAndUpdate(crownCoordinate.getX(),crownCoordinate.getY(),crownCoordinate.getZ());
                playerEntity.sendStatusMessage(new StringTextComponent("Welcome to the "+crownDataBlock.getName()),true);
                return 1;
            }
        }
        return -1;
    }
}
