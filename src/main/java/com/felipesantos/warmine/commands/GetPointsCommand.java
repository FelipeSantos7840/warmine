package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.entities.WarTeam;
import com.felipesantos.warmine.item.WarMineItems;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GetPointsCommand {
    public GetPointsCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("getPoints")
                .then(Commands.argument("quantity", IntegerArgumentType.integer()).executes((command)->{
                    return getPoints(command.getSource(), IntegerArgumentType.getInteger(command,"quantity"));
                })));
    }

    private int getPoints(CommandSource source, Integer quantity) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());

        WarTeam warTeam = player.getWarTeam();

        if(warTeam != null){
            if (warTeam.getScore() >= quantity) {
                ItemStack pointStack = new ItemStack(WarMineItems.POINT.get());
                pointStack.setCount(quantity);

                if(playerEntity.inventory.addItemStackToInventory(pointStack)){
                    warTeam.decrementScore(quantity);
                    source.sendFeedback(new TranslationTextComponent("command.getpoints.success"),true);
                } else {
                    source.sendFeedback(new TranslationTextComponent("command.getpoints.failed1"),true);
                }
            } else {
                source.sendFeedback(new TranslationTextComponent(warTeam.getName()).appendSibling(new TranslationTextComponent("command.getpoints.failed2")),true);
            }
        }

        return -1;
    }
}
