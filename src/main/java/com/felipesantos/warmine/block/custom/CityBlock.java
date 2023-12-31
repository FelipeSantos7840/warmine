package com.felipesantos.warmine.block.custom;

import com.felipesantos.warmine.entities.*;
import com.felipesantos.warmine.util.FileManipuler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;

import javax.annotation.Nullable;
import java.util.UUID;

public class CityBlock extends HorizontalBlock implements IForgeBlock {

    public CityBlock(Properties builder) {
        super(builder);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(!worldIn.isRemote()) {
            boolean isNotValide = true;
            if(placer instanceof PlayerEntity){
                if(World.OVERWORLD == placer.world.getDimensionKey()) {
                    Player player = WarMineData.getPlayer(placer.getName().getString());
                    if (player != null && player.getWarTeam() != null) {
                        if (!(WarMineData.isAboutArea(pos, WarMineData.CITY_RANGE))) {
                            if (player.getWarTeam().getScore() >= 40) {
                                UUID nameInBlockUUID = CrownBlock.createNameInBlock("No Defined Name",worldIn,pos);
                                MinecraftData.warmine.getCities()
                                        .add(new CityDataBlock(new Coordinate(pos.getX(), pos.getY(), pos.getZ()), player.getWarTeam(),nameInBlockUUID));
                                player.getWarTeam().decrementScore(40);
                                isNotValide = false;
                            } else {
                                ((PlayerEntity) placer).sendStatusMessage(new StringTextComponent("Place a city cost 40 points!!"), true);
                            }
                        }
                    }
                }
            }
            if(isNotValide){
                worldIn.destroyBlock(pos, true);
            }
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()) {
            player.sendStatusMessage(new TranslationTextComponent("status.cityblock.rightclick1")
                    .appendSibling(new StringTextComponent(WarMineData.getCity(pos).getName() != null?WarMineData.getCity(pos).getName():"null")
                            .appendSibling(new TranslationTextComponent("status.cityblock.rightclick2")
                                    .appendSibling(new StringTextComponent(WarMineData.getCity(pos).getWarTeam().getName())))), true);
            FileManipuler.warmineSaveData(MinecraftData.warmine);
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }
}
