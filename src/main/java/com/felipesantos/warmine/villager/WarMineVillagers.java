package com.felipesantos.warmine.villager;

import com.felipesantos.warmine.WarMine;
import com.felipesantos.warmine.block.WarMineBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public class WarMineVillagers {
    public static final DeferredRegister<PointOfInterestType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, WarMine.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
            = DeferredRegister.create(ForgeRegistries.PROFESSIONS, WarMine.MOD_ID);

    public static final RegistryObject<PointOfInterestType> MARKET_POINTS_POI = POI_TYPES.register("market_points_poi",
            () -> new PointOfInterestType("market_points_poi", PointOfInterestType.getAllStates(WarMineBlocks.MARKET_BLOCK.get()),1,1));

    public static final RegistryObject<VillagerProfession> POINTS_MERCHANT = VILLAGER_PROFESSIONS.register("points_merchant",
            () -> new VillagerProfession("points_merchant",MARKET_POINTS_POI.get(), ImmutableSet.of(),ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN));

    public static void registerPOIs(){
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class,"registerBlockStates",PointOfInterestType.class).invoke(null,MARKET_POINTS_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
