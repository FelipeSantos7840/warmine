package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.WarTeam;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

import java.util.Comparator;
import java.util.List;

public class LeaderboardCommand {
    public LeaderboardCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("leaderboard").executes((command) -> {
            return getLeaderboard(command.getSource());
        }));
    }

    private int getLeaderboard(CommandSource source){
        if(!(MinecraftData.warmine.getTeams().isEmpty())) {
            List<WarTeam> warTeams = MinecraftData.warmine.getTeams();
            warTeams.sort(Comparator.comparing(WarTeam::getScore));
            StringBuilder strBuilder = new StringBuilder();
            for (WarTeam team : warTeams) {
                strBuilder.append("[Team : \u00A7b"+ team.getName() + "\u00A7r - Score: \u00A7c" + team.getScore() + "\u00A7r],\n");
            }
            source.sendFeedback(new StringTextComponent(strBuilder.toString()),true);
            return 1;
        }
        return -1;
    }
}
