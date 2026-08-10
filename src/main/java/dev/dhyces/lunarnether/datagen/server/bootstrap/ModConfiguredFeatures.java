package dev.dhyces.lunarnether.datagen.server.bootstrap;

import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.Optional;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModFeatures;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.util.ModTagKeys;
import dev.dhyces.lunarnether.worldgen.feature.configs.ExtendedBlockStateConfiguration;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> AIR_DISK = registerKey("air_disk");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRATER_VIA_LAKE = registerKey("crater_via_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ILMENITE = registerKey("ore_ilmenite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_TUFF = registerKey("ore_nether_tuff");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_MOONSTONE_ROCK = registerKey("small_moonstone_rock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_ORE_ROCK = registerKey("small_ore_rock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_TUFF_ROCK = registerKey("small_tuff_rock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRATER_BIG = registerKey("crater_big");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BORDER_DISKS = registerKey("border_disks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BOULDER = registerKey("boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DELTA_SURFACE_CRATER = registerKey("delta_surface_crater");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VALLEY_SURFACE_CRATER = registerKey("valley_surface_crater");


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(
            context,
            AIR_DISK,
            Feature.DISK,
            new DiskConfiguration(
                new RuleBasedBlockStateProvider(
                    SimpleStateProvider.simple(ModBlocks.ASTRALITH.get()),
                    List.of(
                        new RuleBasedBlockStateProvider.Rule(
                            BlockPredicate.matchesBlocks(Vec3i.ZERO, ModBlocks.ASTRALITH.get()),
                            SimpleStateProvider.simple(Blocks.AIR)
                        )
                    )
                ),
                BlockPredicate.matchesBlocks(Blocks.AIR),
                UniformInt.of(1, 2),
                1
            )
        );

        register(
            context,
            CRATER_VIA_LAKE,
            Feature.LAKE,
            new LakeFeature.Configuration(
                SimpleStateProvider.simple(Blocks.AIR),
                SimpleStateProvider.simple(ModBlocks.LUNAR_STONE.get())
            )
        );

        register(
            context,
            SMALL_TUFF_ROCK,
            ModFeatures.ROCK.get(),
            new ExtendedBlockStateConfiguration(
                Blocks.TUFF.defaultBlockState(),
                Optional.empty()
            )
        );

        register(
            context,
            SMALL_MOONSTONE_ROCK,
            ModFeatures.ROCK.get(),
            new ExtendedBlockStateConfiguration(
                ModBlocks.LUNAR_STONE.get().defaultBlockState(),
                Optional.empty()
            )
        );

        register(
            context,
            SMALL_ORE_ROCK,
            ModFeatures.ROCK.get(),
            new ExtendedBlockStateConfiguration(
                ModBlocks.ILMENITE_ORE.get().defaultBlockState(),
                Optional.empty()
            )
        );

        register(
            context,
            ORE_ILMENITE,
            Feature.ORE,
            new OreConfiguration(
                new BlockStateMatchTest(ModBlocks.LUNAR_STONE.get().defaultBlockState()),
                ModBlocks.ILMENITE_ORE.get().defaultBlockState(),
                6,
                0f
            )
        );

        register(
            context,
            ORE_NETHER_TUFF,
            Feature.ORE,
            new OreConfiguration(
                new TagMatchTest(ModTagKeys.Blocks.OUTROCK_BLOB_REPLACEABLES),
                Blocks.TUFF.defaultBlockState(),
                24,
                0f
            )
        );

        register(
            context,
            CRATER_BIG,
            Feature.GEODE,
            new GeodeConfiguration(
                new GeodeBlockSettings(
                    SimpleStateProvider.simple(Blocks.AIR),
                    SimpleStateProvider.simple(Blocks.AIR),
                    SimpleStateProvider.simple(Blocks.AIR),
                    SimpleStateProvider.simple(Blocks.AIR),
                    new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder()
                            .add(ModBlocks.LUNAR_STONE.get().defaultBlockState(), 36)
                            .add(ModBlocks.LUNAR_DUST.get().defaultBlockState(), 12)
                            .add(ModBlocks.ILMENITE_ORE.get().defaultBlockState(), 6)
                            .add(ModBlocks.RAW_ILMENITE_BLOCK.get().defaultBlockState(), 1)
                            .build()),
                    List.of(ModBlocks.ILMENITE_ORE.get().defaultBlockState()),
                    TagKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace("air")),
                    TagKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace("geode_invalid_blocks"))
                ),
                new GeodeLayerSettings(10, 11, 12, 15),
                new GeodeCrackSettings(0.25, 1, 1),
                0,
                0,
                true,
                UniformInt.of(7, 9),
                ConstantInt.of(2),
                UniformInt.of(1, 2),
                -16,
                16,
                0.05,
                5000
            )
        );

    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
    BootstrapContext<ConfiguredFeature<?, ?>> context,
    ResourceKey<ConfiguredFeature<?,?>> key,
    F feature,
    FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
