package dev.dhyces.lunarnether.datagen.server.bootstrap;

import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

import dev.dhyces.lunarnether.LunarNether;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> AIR_DISK_PLACED = registerKey("air_disk_placed");
    public static final ResourceKey<PlacedFeature> CRATER_SMALL_PLACED = registerKey("crater_small_placed");
    public static final ResourceKey<PlacedFeature> SMALL_TUFF_ROCK_PLACED = registerKey("small_tuff_rock_placed");
    public static final ResourceKey<PlacedFeature> SMALL_MOONSTONE_ROCK_PLACED = registerKey("small_moonstone_rock_placed");
    public static final ResourceKey<PlacedFeature> SMALL_ORE_ROCK_PLACED = registerKey("small_ore_rock_placed");
    public static final ResourceKey<PlacedFeature> ORE_ILMENITE_PLACED = registerKey("ore_ilmenite_placed");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_TUFF_PLACED = registerKey("ore_nether_tuff_placed");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_OBSIDIAN_PLACED = registerKey("ore_nether_obsidian_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(
            context,
            AIR_DISK_PLACED,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.AIR_DISK),
            List.of(
                CountPlacement.of(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(127))),
                EnvironmentScanPlacement.scanningFor(
                    Direction.DOWN,
                    BlockPredicate.not(BlockPredicate.solid()),
                    2
                )
            )
        );

        register(
            context,
            CRATER_SMALL_PLACED,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.CRATER_VIA_LAKE),
            List.of(
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome()
            )
        );

        register(
            context,
            SMALL_TUFF_ROCK_PLACED,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.SMALL_TUFF_ROCK),
            List.of(
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                RandomOffsetPlacement.horizontal(ConstantInt.of(7)),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome()
            )
        );

        register(
            context,
            SMALL_MOONSTONE_ROCK_PLACED,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.SMALL_MOONSTONE_ROCK),
            List.of(
                RarityFilter.onAverageOnceEvery(8),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome()
            )
        );

        register(
            context,
            SMALL_ORE_ROCK_PLACED,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.SMALL_MOONSTONE_ROCK),
            List.of(
                RarityFilter.onAverageOnceEvery(24),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome()
            )
        );

        register(
            context,
            ORE_ILMENITE_PLACED,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_ILMENITE),
            List.of(
                CountPlacement.of(40),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(127), VerticalAnchor.absolute(176))),
                BiomeFilter.biome()
            )
        );

        register(
            context,
            ORE_NETHER_OBSIDIAN_PLACED,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_NETHER_OBSIDIAN),
            List.of(
                RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(120), VerticalAnchor.absolute(124)))
            )
        );

        register(
            context,
            ORE_NETHER_TUFF_PLACED,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_NETHER_TUFF),
            List.of(
                RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(128), VerticalAnchor.absolute(132)))
            )
        );

    }

    private static void register(
        BootstrapContext<PlacedFeature> context,
        ResourceKey<PlacedFeature> key,
        Holder<ConfiguredFeature<?, ?>> configuration,
        List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, name));
    }
}
