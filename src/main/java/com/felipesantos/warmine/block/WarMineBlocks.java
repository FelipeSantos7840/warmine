package com.felipesantos.warmine.block;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.block.custom.CityBlock;
import com.felipesantos.warmine.block.custom.CrownBlock;
import com.felipesantos.warmine.block.custom.MarketBlock;
import com.felipesantos.warmine.groups.WarMineGroup;
import com.felipesantos.warmine.item.WarMineItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class WarMineBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WarMine.MOD_ID);

    public static final RegistryObject<Block> CROWN_BLOCK = registerBlock("crown_block", ()->new CrownBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)
            .setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(2).hardnessAndResistance(50)));

    public static final RegistryObject<Block> CITY_BLOCK = registerBlock("city_block", ()-> new CityBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)
            .setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(2).hardnessAndResistance(20)));

    public static final RegistryObject<Block> MARKET_BLOCK = registerBlock("market_block",()-> new MarketBlock(AbstractBlock.Properties.from(Blocks.STONE)
            .setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(2).hardnessAndResistance(10)));


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);

        registerBlockItem(name,toReturn);

        return toReturn;
    }

    private static <T extends Block>void registerBlockItem(String name, RegistryObject<T> block){
        WarMineItems.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().group(WarMineGroup.WAR_GROUP)));
    }

    public static void registerBus(IEventBus event){
        BLOCKS.register(event);
    }
}
