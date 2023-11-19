package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.CityDataBlock;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.item.WarMineItems;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;

public class ToCityCommand {
    public ToCityCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("toCity")
                .then(Commands.argument("cityName",StringArgumentType.string()).executes((command) -> {
            return toCity(command.getSource(),StringArgumentType.getString(command,"cityName"));
        })));
    }

    private int toCity(CommandSource source,String cityName) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        CityDataBlock cityBlock = WarMineData.getCity(cityName);
        if(cityBlock != null){
            if(player.getWarTeam() != null && cityBlock.getWarTeam().equals(player.getWarTeam())){
                ItemStack itemStack = playerEntity.getHeldItemMainhand();
                if(itemStack != null && itemStack.getItem() == WarMineItems.CITY_RUNE.get()){
                    itemStack.damageItem(1,playerEntity,p -> p.sendBreakAnimation(Hand.MAIN_HAND));
                    playerEntity.setPositionAndUpdate(cityBlock.getCoordinate().getX(),cityBlock.getCoordinate().getY()+1,cityBlock.getCoordinate().getZ());
                    playerEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA,120));
                    return 1;
                } else {
                    source.sendFeedback(new StringTextComponent("You need a City Rune in Main Hand!"),true);
                }
            } else {
                source.sendFeedback(new StringTextComponent("Invalid City to Teleport!"),true);
            }
        } else {
            source.sendFeedback(new StringTextComponent("City not found!"),true);
        }
        return -1;
    }
}
