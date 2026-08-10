package dev.dhyces.lunarnether.datagen.server.bootstrap;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.util.ModTagKeys;

public class ModConfiguredCarvers {

    public static final ResourceKey<ConfiguredWorldCarver<?>> MOON_CAVE = registerKey("moon_cave");


    public static ResourceKey<ConfiguredWorldCarver<?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        register(
            context,
            MOON_CAVE,
            new ConfiguredWorldCarver<>(
                WorldCarver.CAVE,
                new CaveCarverConfiguration(
                    0.05f,
                    UniformHeight.of(VerticalAnchor.aboveBottom(128), VerticalAnchor.belowTop(0)),
                    UniformFloat.of(0.1f, 0.9f),
                    VerticalAnchor.aboveBottom(8),
                    CarverDebugSettings.of(
                        Blocks.CRIMSON_BUTTON.defaultBlockState(),
                        Blocks.CANDLE.defaultBlockState(),
                        Blocks.LAVA.defaultBlockState(),
                        Blocks.GLASS.defaultBlockState()),
                    BuiltInRegistries.BLOCK.getOrCreateTag(ModTagKeys.Blocks.MOON_CARVER_REPLACEABLES),
                    UniformFloat.of(0.7f, 1.4f),
                    UniformFloat.of(0.8f, 1.3f),
                    UniformFloat.of(-1f, -0.4f)
                )
            )
        );
    }

    private static void register(BootstrapContext<ConfiguredWorldCarver<?>> context, ResourceKey<ConfiguredWorldCarver<?>> carverKey, ConfiguredWorldCarver<?> carver) {
        context.register(carverKey, carver);
    }
}
