package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.WarMineData;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class DayOfWarCommand {
    public DayOfWarCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("dayOfWar").requires(source -> source.hasPermissionLevel(4)).executes((command) -> {
            return alternDayOfWar(command.getSource());
        }));
    }

    private int alternDayOfWar(CommandSource source){
        if(WarMineData.DAY_OF_WAR){
            WarMineData.DAY_OF_WAR = false;
            source.sendFeedback(new StringTextComponent("Day of War: false"),true);
        } else {
            WarMineData.DAY_OF_WAR = true;
            source.sendFeedback(new StringTextComponent("Day of War: true"),true);
        }
        return 1;
    }
}
