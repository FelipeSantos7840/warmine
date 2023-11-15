package com.felipesantos.warmine.block.custom;

import com.felipesantos.warmine.entities.*;
import com.felipesantos.warmine.util.FileManipuler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class CrownBlock extends HorizontalBlock {
    public CrownBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(!worldIn.isRemote()) {
            boolean isNotValide = true;
            if (placer instanceof PlayerEntity) {
                Player player = WarMineData.getPlayer(placer.getName().getString());
                if (player != null && player.getWarTeam() != null) {
                    if(!(WarMineData.teamAlreadyHaveACapital(player.getWarTeam()))){
                        if(!(WarMineData.isAboutArea(pos,WarMineData.CROWN_RANGE))){
                            MinecraftData.warmine.getCapitals()
                                    .add(new CrownDataBlock(new Coordinate(pos.getX(), pos.getY(), pos.getZ()), player.getWarTeam()));
                            isNotValide = false;
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
                        shrinkValue = isAnimalItem(itemStack,warTeam);
                        if(shrinkValue == -1){
                            shrinkValue = isFarmItem(itemStack,warTeam);
                            if(shrinkValue == -1){
                                shrinkValue = isOreItem(itemStack,warTeam);
                                if(shrinkValue == -1){
                                    shrinkValue = isBossItem(itemStack,warTeam);
                                }
                            }
                        }
                        if(shrinkValue != -1){
                            itemStack.shrink(shrinkValue);
                        }
                        if(itemStack.isEmpty()){
                            player.inventory.deleteStack(itemStack);
                        }
                    }
                }
            }
        }
        player.sendStatusMessage(new StringTextComponent("City of "+ WarMineData.getCapital(pos).getName()+"! Capital of " + WarMineData.getCapital(pos).getWarTeam().getName()),true);
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

    private int isAnimalItem(ItemStack itemStack,WarTeam warTeam){
        Item item = itemStack.getItem();
        if(item == Items.BEEF || item == Items.CHICKEN || item == Items.PORKCHOP){
            return validateItemQuantity(itemStack,64,5,warTeam);
        }
        return -1;
    }

    private int isFarmItem(ItemStack itemStack,WarTeam warTeam){
        Item item = itemStack.getItem();
        if(item == Items.HAY_BLOCK){
            return validateItemQuantity(itemStack,32,7,warTeam);
        } else if (item == Items.CARROT || item == Items.POTATO || item == Items.BAMBOO || item == Items.CACTUS){
            return validateItemQuantity(itemStack,64,3,warTeam);
        } else if (item == Items.NETHER_WART){
            return validateItemQuantity(itemStack,64,7,warTeam);
        } else if (item == Items.BROWN_MUSHROOM || item == Items.RED_MUSHROOM){
            return validateItemQuantity(itemStack,24,5,warTeam);
        }
        return -1;
    }

    private int isOreItem(ItemStack itemStack,WarTeam warTeam) {
        Item item = itemStack.getItem();
        if(item == Items.IRON_BLOCK || item == Items.GOLD_BLOCK){
            return validateItemQuantity(itemStack,32,10,warTeam);
        } else if(item == Items.DIAMOND_BLOCK){
            return validateItemQuantity(itemStack,16,8,warTeam);
        } else if(item == Items.NETHERITE_BLOCK){
            return validateItemQuantity(itemStack,5,30,warTeam);
        }
        return -1;
    }

    private int isBossItem(ItemStack itemStack,WarTeam warTeam){
        Item item = itemStack.getItem();
        if (item == Items.DRAGON_EGG) {
            return validateItemQuantity(itemStack,1,50,warTeam);
        } else if(item == Items.NETHER_STAR){
            return validateItemQuantity(itemStack,1,55,warTeam);
        }
        return -1;
    }

    private int validateItemQuantity(ItemStack itemStack,int condition,int incrementQuantity, WarTeam warTeam){
        if(itemStack.getCount() >= condition){
            warTeam.incrementScore(incrementQuantity);
            return condition;
        }
        return -1;
    }
}
