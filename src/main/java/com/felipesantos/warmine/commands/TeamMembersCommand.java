package com.felipesantos.warmine.commands;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.entities.WarTeam;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMembersCommand {
    public TeamMembersCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("teamMembers").executes((command) -> {
            return teamMembers(command.getSource());
        }));
    }

    private int teamMembers(CommandSource source) throws CommandSyntaxException {
        PlayerEntity playerEntity = source.asPlayer();
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        WarTeam playerWarTeam = player.getWarTeam();

        if(playerWarTeam != null){
            List<Player> players = MinecraftData.warmine.getPlayers().stream().filter((p) -> playerWarTeam.equals(p.getWarTeam())).collect(Collectors.toList());
            StringBuilder strBuilder = new StringBuilder("--------- "+playerWarTeam.getName()+" ---------\n");
            for(Player playerUnique : players){
                strBuilder.append("[ "+playerUnique.getName()+" ],");
            }
            source.sendFeedback(new StringTextComponent(strBuilder.toString()),true);
            return 1;
        } else {
            source.sendFeedback(new TranslationTextComponent("command.teammembers.failed"), true);
        }

        return -1;
    }
}
