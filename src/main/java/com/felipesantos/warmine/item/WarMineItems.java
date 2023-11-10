package com.felipesantos.warmine.item;

import com.felipesantos.warmine.WarMine;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WarMineItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WarMine.MOD_ID);

    public static void registerBus(IEventBus event){
        ITEMS.register(event);
    }
}
