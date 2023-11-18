package com.felipesantos.warmine.item.custom;

import com.felipesantos.warmine.entities.MinecraftData;
import com.felipesantos.warmine.entities.Player;
import com.felipesantos.warmine.entities.WarMineData;
import com.felipesantos.warmine.entities.WarTeam;
import com.felipesantos.warmine.events.WarSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Horn extends Item {

    public Horn(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote()){
            ItemStack itemStack = playerIn.getHeldItem(handIn);

            addEffectsToTeam(worldIn,playerIn);

            worldIn.playSound(null,playerIn.getPosition(), WarSoundEvents.WAR_HORN.get(), SoundCategory.BLOCKS,0.6f,1);
            itemStack.damageItem(1,playerIn, playerEntity -> playerEntity.sendBreakAnimation(handIn));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void addEffectsToTeam(World world, PlayerEntity playerEntity){
        Player player = WarMineData.getPlayer(playerEntity.getName().getString());
        if(player.getWarTeam() != null){
            WarTeam warTeam = player.getWarTeam();
            List<Player> playersOfTeam = MinecraftData.warmine.getPlayers()
                    .stream().filter((warPlayer) -> warPlayer.getWarTeam().equals(warTeam)).collect(Collectors.toList());
            for (Player p : playersOfTeam) {
                for (PlayerEntity pEntity : world.getPlayers()) {
                    if (p.getName().equalsIgnoreCase(pEntity.getName().getString())) {
                        addHornEffetsToPlayer(playerEntity);
                    }
                }
            }
        } else {
            addHornEffetsToPlayer(playerEntity);
        }
    }

    private void addHornEffetsToPlayer(PlayerEntity playerEntity){
        playerEntity.addPotionEffect(new EffectInstance(Effects.ABSORPTION,3000));
        playerEntity.addPotionEffect(new EffectInstance(Effects.HASTE,6000));
        playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST,6000));
        playerEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION,3000));
    }
}
