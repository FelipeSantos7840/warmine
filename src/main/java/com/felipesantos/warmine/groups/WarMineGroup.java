package com.felipesantos.warmine.groups;

import com.felipesantos.warmine.block.WarMineBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WarMineGroup {
    public static final ItemGroup WAR_GROUP = new ItemGroup("warminegroup") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(WarMineBlocks.CROWN_BLOCK.get());
        }
    };
}
