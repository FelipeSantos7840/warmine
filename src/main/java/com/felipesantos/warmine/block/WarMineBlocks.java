package com.felipesantos.warmine.block;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.item.WarMineItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class WarMineBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WarMine.MOD_ID);



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);

        registerBlockItem(name,toReturn);

        return toReturn;
    }

    private static <T extends Block>void registerBlockItem(String name, RegistryObject<T> block){
        WarMineItems.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    }

    public static void registerBus(IEventBus event){
        BLOCKS.register(event);
    }
}
