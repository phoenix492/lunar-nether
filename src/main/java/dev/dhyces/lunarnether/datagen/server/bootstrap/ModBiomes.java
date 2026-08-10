package dev.dhyces.lunarnether.datagen.server.bootstrap;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeSpecialEffectsBuilder;

import dev.dhyces.lunarnether.LunarNether;

public class ModBiomes {
    public static final ResourceKey<Biome> OUTROCKS = registerKey("outrocks");

    public static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, name));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        register(
            context,
            OUTROCKS,
            new Biome.BiomeBuilder()
                .downfall(0f)
                .specialEffects(
                    BiomeSpecialEffectsBuilder.create(
                        0,
                        4159204,
                        329011,
                        0
                    ).ambientAdditionsSound(
                        new AmbientAdditionsSettings(
                            SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS,
                            0.0111
                        )
                    ).ambientLoopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                    .ambientMoodSound(
                        new AmbientMoodSettings(
                            SoundEvents.AMBIENT_BASALT_DELTAS_MOOD,
                            6000,
                            8,
                            2
                        )
                    ).backgroundMusic(
                        new Music(
                            SoundEvents.MUSIC_BIOME_BASALT_DELTAS,
                            12000,
                            24000,
                            false
                        )
                    ).ambientParticle(
                        new AmbientParticleSettings(
                            ParticleTypes.WHITE_ASH,
                            0.025f
                        )
                    ).build()
                ).temperature(0.9f)
                .generationSettings(
                    new BiomeGenerationSettings.PlainBuilder()
                        .addFeature(
                            GenerationStep.Decoration.LAKES,
                            placedFeatures.getOrThrow(ModPlacedFeatures.CRATER_SMALL_PLACED)
                        ).addFeature(
                            GenerationStep.Decoration.LAKES,
                            placedFeatures.getOrThrow(ModPlacedFeatures.BIG_CRATER_PLACED)
                        ).addFeature(
                            GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                            placedFeatures.getOrThrow(ModPlacedFeatures.SMALL_TUFF_ROCK_PLACED)
                        ).addFeature(
                            GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                            placedFeatures.getOrThrow(ModPlacedFeatures.SMALL_MOONSTONE_ROCK_PLACED)
                        ).addFeature(
                            GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                            placedFeatures.getOrThrow(ModPlacedFeatures.SMALL_ORE_ROCK_PLACED)
                        ).addFeature(
                            GenerationStep.Decoration.STRONGHOLDS,
                            placedFeatures.getOrThrow(ModPlacedFeatures.AIR_DISK_PLACED)
                        ).addFeature(
                            GenerationStep.Decoration.UNDERGROUND_ORES,
                            placedFeatures.getOrThrow(ModPlacedFeatures.ORE_ILMENITE_PLACED)
                        ).addFeature(
                            GenerationStep.Decoration.UNDERGROUND_ORES,
                            placedFeatures.getOrThrow(ModPlacedFeatures.ORE_NETHER_TUFF_PLACED)
                        ).build()
                ).hasPrecipitation(false)
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .build()
        );
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> biomeKey, Biome biome) {
        context.register(biomeKey, biome);
    }
}
