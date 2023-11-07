package com.felipesantos.warmine.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;


public class CrownBlock extends Block {
    public CrownBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(!worldIn.isRemote()){
            if(placer instanceof PlayerEntity){

            }
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    private int[] getCoordinatePos(BlockPos pos){
        return new int[]{pos.getX(),pos.getY(), pos.getZ()};
    }

}
