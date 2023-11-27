package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.AbstractCityBlock;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.entities.WarTeam;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class NameTerritoryCommand {
    public NameTerritoryCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("nameTerritory")
                .then(Commands.argument("name", StringArgumentType.string()).executes((command)->{
            return nameTerritory(command.getSource(),StringArgumentType.getString(command, "name"));
        })));
    }

    private int nameTerritory(CommandSource source, String name) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        AbstractCityBlock territoryBlock = WarMineData.getTerritoryBlockInArea(playerEntity.getPosition());
        WarTeam playerWarTeam = player.getWarTeam();

        if(territoryBlock != null){
            if(territoryBlock.getWarTeam().equals(playerWarTeam)){
                territoryBlock.setName(name);
                if(territoryBlock.getNameUUID() != null) {
                    setCustomName(source.getWorld(), territoryBlock.getNameUUID(), name);
                }
                source.sendFeedback(new TranslationTextComponent("command.nameterritory.success"),true);
                return 1;
            } else {
                source.sendFeedback(new TranslationTextComponent("command.nameterritory.failed"),true);
            }
        }
        return -1;
    }

    public void setCustomName(ServerWorld serverWorld, UUID entityUUID, String name){
        if(entityUUID != null && !serverWorld.isRemote()){
            Entity entityBlock = serverWorld.getEntityByUuid(entityUUID);
            if(entityBlock != null){
                entityBlock.setCustomName(new StringTextComponent(name));
            }
        }
    }
}
