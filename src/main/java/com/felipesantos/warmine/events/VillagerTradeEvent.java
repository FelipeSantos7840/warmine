package com.felipesantos.warmine.events;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.block.WarMineBlocks;
import com.felipesantos.warmine.item.WarMineItems;
import com.felipesantos.warmine.villager.WarMineVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = WarMine.MOD_ID)
public class VillagerTradeEvent {
    @SubscribeEvent
    public static void addVillageTrade(VillagerTradesEvent event){
        if(event.getType() == WarMineVillagers.POINTS_MERCHANT.get()){
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();
            int villagerLevel = 1;

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),5),new ItemStack(Items.IRON_PICKAXE,1),10,6,0.05f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),5),new ItemStack(Items.IRON_AXE,1),10,6,0.05f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),3),new ItemStack(Items.SHIELD,1),12,6,0.05f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),7),new ItemStack(Items.CACTUS,16),12,7,0.02f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),9),new ItemStack(Items.POTATO,16),12,7,0.02f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),10),new ItemStack(Items.BEETROOT,16),12,7,0.02f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),7),new ItemStack(Items.SUGAR_CANE,10),12,5,0.03f
            )));

            villagerLevel++;

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),15),new ItemStack(Items.DIAMOND_PICKAXE,1),7,9,0.07f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),15),new ItemStack(Items.DIAMOND_AXE,1),7,9,0.07f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),14),new ItemStack(Items.DIAMOND_SWORD,1),7,9,0.07f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),20),new ItemStack(Items.PUMPKIN,16),5,9,0.05f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),22),new ItemStack(Items.BAMBOO,16),5,9,0.05f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),25),new ItemStack(Items.SADDLE,1),9,9,0.10f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),30),new ItemStack(Items.CAKE,1),8,9,0.07f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),10),new ItemStack(Items.GOLDEN_APPLE,8),20,9,0.10f
            )));


            villagerLevel++;

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),40),new ItemStack(Items.NETHER_WART,5),6,12,0.09f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),30),new ItemStack(Items.RED_MUSHROOM,5),6,12,0.09f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),30),new ItemStack(Items.BROWN_MUSHROOM,5),6,12,0.09f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),40),new ItemStack(Items.TNT,16),15,12,0.09f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),35),new ItemStack(Items.ENDER_PEARL,8),7,12,0.09f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),15),new ItemStack(Items.LEATHER_HORSE_ARMOR,1),7,8,0.05f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),25),new ItemStack(Items.IRON_HORSE_ARMOR,1),7,10,0.07f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),45),new ItemStack(Items.DIAMOND_HORSE_ARMOR,1),3,12,0.15f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),15),new ItemStack(Items.OBSIDIAN,8),15,12,0.07f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),50),new ItemStack(WarMineItems.HORN.get(),1),6,12,0.10f
            )));

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),80),new ItemStack(WarMineBlocks.CITY_BLOCK.get(),1),20,15,0.15f
            )));

            villagerLevel++;

            trades.get(villagerLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(WarMineItems.POINT.get(),65),new ItemStack(WarMineItems.CITY_RUNE.get(),1),5,20,0.2f
            )));
        }
    }
}
