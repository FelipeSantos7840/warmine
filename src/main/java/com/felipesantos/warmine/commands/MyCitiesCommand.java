package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;
import java.util.stream.Collectors;

public class MyCitiesCommand {
    public MyCitiesCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("myCities").executes((command) -> {
            return myCities(command.getSource());
        }));
    }

    public int myCities(CommandSource source) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        WarTeam playerWarTeam = player.getWarTeam();

        if (playerWarTeam != null) {
            List<CityDataBlock> cities = MinecraftData.warmine.getCities().stream().filter((city) -> city.getWarTeam().equals(playerWarTeam)).collect(Collectors.toList());
            if(!cities.isEmpty()){
                StringBuilder strBuilder = new StringBuilder("--CITIES--\n");
                for(CityDataBlock city : cities){
                    strBuilder.append("City Name:\u00A7c"+city.getName()+"\u00A7r | City Coordinate: "+city.getCoordinate()+"\n");
                }
                source.sendFeedback(new StringTextComponent(strBuilder.toString()),true);
                return 1;
            }
        }
        return -1;
    }
}
