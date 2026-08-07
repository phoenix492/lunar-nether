package dev.dhyces.lunarnether.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.biome.BiomeSource;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.worldgen.HeightedBiomeSource;

public class ModBiomeSources {
    public static final DeferredRegister<MapCodec<? extends BiomeSource>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BIOME_SOURCE, LunarNether.MODID);

    public static final DeferredHolder<MapCodec<? extends BiomeSource>, MapCodec<HeightedBiomeSource>> HEIGHTED = REGISTRY.register("heighted", () -> HeightedBiomeSource.CODEC);
}