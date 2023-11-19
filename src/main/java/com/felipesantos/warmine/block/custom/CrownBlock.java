package com.felipesantos.warmine.block.custom;

import com.felipesantos.warmine.entities.*;
import com.felipesantos.warmine.util.FileManipuler;
import com.felipesantos.warmine.util.SellItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class CrownBlock extends HorizontalBlock {
    public CrownBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(!worldIn.isRemote()) {
            boolean isNotValide = true;
            if (placer instanceof PlayerEntity) {
                if(World.OVERWORLD == placer.world.getDimensionKey()) {
                    Player player = WarMineData.getPlayer(placer.getName().getString());
                    if (player != null && player.getWarTeam() != null) {
                        if (!(WarMineData.teamAlreadyHaveACapital(player.getWarTeam()))) {
                            if (!(WarMineData.isAboutArea(pos, WarMineData.CROWN_RANGE))) {
                                MinecraftData.warmine.getCapitals()
                                        .add(new CrownDataBlock(new Coordinate(pos.getX(), pos.getY(), pos.getZ()), player.getWarTeam()));
                                isNotValide = false;
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
        if(!worldIn.isRemote()){
            Player warPlayer = WarMineData.getPlayer(player.getName().getString());
            CrownDataBlock crownBlock = WarMineData.getCapital(pos);
            WarTeam warTeam = warPlayer.getWarTeam();
            if(warTeam != null) {
                if (warTeam.equals(crownBlock.getWarTeam())) {
                    int shrinkValue;
                    for (ItemStack itemStack : player.inventory.mainInventory) {
                        shrinkValue = SellItems.sellItem(itemStack,warTeam);
                        if(shrinkValue != -1){
                            itemStack.shrink(shrinkValue);
                        }
                        if(itemStack.isEmpty()){
                            player.inventory.deleteStack(itemStack);
                        }
                    }
                    player.sendStatusMessage(new StringTextComponent(crownBlock.getWarTeam().getName()+" has " + warTeam.getScore()+" points!"),true);
                } else {
                    player.sendStatusMessage(new StringTextComponent("City of "+ WarMineData.getCapital(pos).getName()+"! Capital of " + WarMineData.getCapital(pos).getWarTeam().getName()),true);
                }
                FileManipuler.warmineSaveData(MinecraftData.warmine);
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
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
