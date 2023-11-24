package com.felipesantos.warmine.item;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.groups.WarMineGroup;
import com.felipesantos.warmine.item.custom.Horn;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WarMineItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WarMine.MOD_ID);

    public static RegistryObject<Item> POINT = ITEMS.register("point",()-> new Item(new Item.Properties().group(WarMineGroup.WAR_GROUP)));
    public static RegistryObject<Item> HORN = ITEMS.register("horn",() -> new Horn(new Item.Properties().group(WarMineGroup.WAR_GROUP)
            .maxDamage(15)));
    public static RegistryObject<Item> CITY_RUNE = ITEMS.register("city_rune",() -> new Item(new Item.Properties().group(WarMineGroup.WAR_GROUP)
            .maxDamage(5)));

    //SELLING ITEMS
    public static RegistryObject<Item> ROTTEN_FLESH_BAG = ITEMS.register("rotten_flesh_bag",() -> new Item(new Item.Properties().group(WarMineGroup.WAR_GROUP)));
    public static RegistryObject<Item> PRISMARINE_SHARD_BAG = ITEMS.register("prismarine_shard_bag",() -> new Item(new Item.Properties().group(WarMineGroup.WAR_GROUP)));
    public static RegistryObject<Item> PHANTOM_MEMBRANE_BAG = ITEMS.register("phantom_membrane_bag",() -> new Item(new Item.Properties().group(WarMineGroup.WAR_GROUP)));
    public static RegistryObject<Item> MAGMA_CREAM_BAG = ITEMS.register("magma_cream_bag",() -> new Item(new Item.Properties().group(WarMineGroup.WAR_GROUP)));
    public static RegistryObject<Item> BLAZE_ROD_BAG = ITEMS.register("blaze_rod_bag",() -> new Item(new Item.Properties().group(WarMineGroup.WAR_GROUP)));
    public static RegistryObject<Item> GHAST_TEAR_BOTTLE = ITEMS.register("ghast_tear_bottle",() -> new Item(new Item.Properties().group(WarMineGroup.WAR_GROUP)));

    //----- -_- ------

    public static void registerBus(IEventBus event){
        ITEMS.register(event);
    }
}
