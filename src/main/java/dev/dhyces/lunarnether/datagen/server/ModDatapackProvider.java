package dev.dhyces.lunarnether.datagen.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;

import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.datagen.server.bootstrap.ModBiomes;
import dev.dhyces.lunarnether.datagen.server.bootstrap.ModConfiguredCarvers;
import dev.dhyces.lunarnether.datagen.server.bootstrap.ModConfiguredFeatures;
import dev.dhyces.lunarnether.datagen.server.bootstrap.ModPlacedFeatures;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
        .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
        .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
        .add(Registries.BIOME, ModBiomes::bootstrap)
        .add(Registries.CONFIGURED_CARVER, ModConfiguredCarvers::bootstrap);

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(LunarNether.MODID));
    }
}
