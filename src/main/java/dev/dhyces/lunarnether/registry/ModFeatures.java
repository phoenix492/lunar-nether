package dev.dhyces.lunarnether.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.worldgen.feature.RockFeature;
import dev.dhyces.lunarnether.worldgen.feature.configs.ExtendedBlockStateConfiguration;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> MOD_FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, LunarNether.MODID);

    public static final DeferredHolder<Feature<?>, RockFeature> ROCK = MOD_FEATURES.register("rock", () -> new RockFeature(ExtendedBlockStateConfiguration.CODEC));

}
