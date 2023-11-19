package com.felipesantos.warmine.block.custom;

import com.felipesantos.warmine.entities.*;
import com.felipesantos.warmine.util.FileManipuler;
import com.felipesantos.warmine.util.SellItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
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

public class MarketBlock extends HorizontalBlock {

    public MarketBlock(Properties builder) {
        super(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()){
            Player warPlayer = WarMineData.getPlayer(player.getName().getString());
            WarTeam warTeam = warPlayer.getWarTeam();
            if(warTeam != null) {
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
                FileManipuler.warmineSaveData(MinecraftData.warmine);
            } else {
                player.sendStatusMessage(new StringTextComponent("You don't have a team!"),true);
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
}
