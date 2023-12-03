package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.entities.Coordinate;
import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.WarMineData;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class SetLobbyCommand {
    public SetLobbyCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("warmine")
                .then(Commands.literal("setServerLobby")
                        .requires(source -> source.hasPermissionLevel(4)).executes(command ->{
                            return setLobby(command.getSource());
                        })));
    }

    private int setLobby(CommandSource source){
        if(!source.getWorld().isRemote) {
            if (WarMine.IS_POSSIBLE) {
                PlayerEntity player = (PlayerEntity) source.getEntity();
                if(player != null) {
                    MinecraftData.warmine.getConfigData().setLobbyCoord(Coordinate.getCoordinateOfPos(player.getPosition()));
                    source.sendFeedback(new TranslationTextComponent("command.setlobby.success"),true);
                }
                return 1;
            } else {
                source.sendFeedback(new TranslationTextComponent("command.setlobby.failed1"),true);
            }
        }
        return -1;
    }
}
