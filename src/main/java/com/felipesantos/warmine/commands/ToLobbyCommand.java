package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.Coordinate;
import com.felipesantos.warmine.entities.MinecraftData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class ToLobbyCommand {
    public ToLobbyCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("toLobby").executes((command) -> {
            return toLobby(command.getSource());
        }));
    }

    private int toLobby(CommandSource source) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        if(MinecraftData.warmine.getConfigData() != null){
            Coordinate coordinate = MinecraftData.warmine.getConfigData().getLobbyCoord();
            if(coordinate != null){
                playerEntity.setPositionAndUpdate(coordinate.getX(),coordinate.getY(),coordinate.getZ());
                source.sendFeedback(new TranslationTextComponent("command.tolobby.success"),true);
            } else {
                source.sendFeedback(new TranslationTextComponent("command.tolobby.failed2"),true);
            }
        } else {
            source.sendFeedback(new TranslationTextComponent("command.tolobby.failed1"),true);
        }
        return -1;
    }
}
