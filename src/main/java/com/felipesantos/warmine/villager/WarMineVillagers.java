package com.felipesantos.warmine.villager;

import com.felipesantos.warmine.WarMine;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WarMineVillagers {
    public static final DeferredRegister<PointOfInterestType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, WarMine.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
            = DeferredRegister.create(ForgeRegistries.PROFESSIONS, WarMine.MOD_ID);

    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
