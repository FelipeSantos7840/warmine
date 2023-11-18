package com.felipesantos.warmine.item;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.item.custom.Horn;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WarMineItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WarMine.MOD_ID);

    public static RegistryObject<Item> POINT = ITEMS.register("point",()-> new Item(new Item.Properties().group(ItemGroup.MISC)));
    public static RegistryObject<Item> HORN = ITEMS.register("horn",() -> new Horn(new Item.Properties().group(ItemGroup.MISC).maxDamage(15)));

    public static void registerBus(IEventBus event){
        ITEMS.register(event);
    }
}
