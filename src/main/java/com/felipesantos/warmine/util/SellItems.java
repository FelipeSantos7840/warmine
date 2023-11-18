package com.felipesantos.warmine.util;

import com.felipesantos.warmine.entities.WarTeam;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SellItems {
    public static int sellItem(ItemStack itemStack, WarTeam warTeam){
        int shrinkValue = SellItems.isAnimalItem(itemStack,warTeam);
        if(shrinkValue == -1){
            shrinkValue = SellItems.isFarmItem(itemStack,warTeam);
            if(shrinkValue == -1){
                shrinkValue = SellItems.isOreItem(itemStack,warTeam);
                if(shrinkValue == -1){
                    shrinkValue = SellItems.isBossItem(itemStack,warTeam);
                }
            }
        }
        return shrinkValue;
    }
    public static int isAnimalItem(ItemStack itemStack, WarTeam warTeam){
        Item item = itemStack.getItem();
        if(item == Items.BEEF){
            return validateItemQuantity(itemStack,64,4,warTeam);
        } else if(item == Items.CHICKEN || item == Items.PORKCHOP){
            return validateItemQuantity(itemStack,64,5,warTeam);
        } else if(item == Items.LEATHER){
            return validateItemQuantity(itemStack,64,3,warTeam);
        } else if(item == Items.EGG){
            return validateItemQuantity(itemStack, 16, 1, warTeam);
        }
        return -1;
    }

    public static int isFarmItem(ItemStack itemStack,WarTeam warTeam){
        Item item = itemStack.getItem();
        if(item == Items.HAY_BLOCK){
            return validateItemQuantity(itemStack,32,7,warTeam);
        } else if (item == Items.CARROT || item == Items.POTATO || item == Items.BAMBOO || item == Items.CACTUS || item == Items.SUGAR_CANE || item == Items.BEETROOT){
            return validateItemQuantity(itemStack,64,3,warTeam);
        } else if (item == Items.NETHER_WART){
            return validateItemQuantity(itemStack,64,7,warTeam);
        } else if (item == Items.BROWN_MUSHROOM || item == Items.RED_MUSHROOM){
            return validateItemQuantity(itemStack,32,5,warTeam);
        } else if (item == Items.PUMPKIN){
            return validateItemQuantity(itemStack,64,4,warTeam);
        }
        return -1;
    }

    public static int isOreItem(ItemStack itemStack,WarTeam warTeam) {
        Item item = itemStack.getItem();
        if(item == Items.IRON_BLOCK || item == Items.GOLD_BLOCK){
            return validateItemQuantity(itemStack,32,15,warTeam);
        } else if(item == Items.REDSTONE_BLOCK){
            return validateItemQuantity(itemStack,64,9,warTeam);
        }else if(item == Items.DIAMOND_BLOCK){
            return validateItemQuantity(itemStack,16,15,warTeam);
        } else if(item == Items.NETHERITE_BLOCK){
            return validateItemQuantity(itemStack,5,30,warTeam);
        }
        return -1;
    }

    public static int isBossItem(ItemStack itemStack,WarTeam warTeam){
        Item item = itemStack.getItem();
        if (item == Items.DRAGON_EGG) {
            return validateItemQuantity(itemStack,1,50,warTeam);
        } else if(item == Items.NETHER_STAR){
            return validateItemQuantity(itemStack,1,35,warTeam);
        }
        return -1;
    }

    public static int validateItemQuantity(ItemStack itemStack,int condition,int incrementQuantity, WarTeam warTeam){
        if(itemStack.getCount() >= condition){
            warTeam.incrementScore(incrementQuantity);
            return condition;
        }
        return -1;
    }
}
