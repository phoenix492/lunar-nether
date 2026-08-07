package dev.dhyces.lunarnether;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.dhyces.biomeextensions.BiomeExtensionsMod;
import dev.dhyces.lunarnether.registry.ModBiomeModifiers;
import dev.dhyces.lunarnether.registry.ModFeatures;
import dev.dhyces.lunarnether.registry.ModBiomeSources;
import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModCreativeModTabs;
import dev.dhyces.lunarnether.registry.ModFluids;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.registry.ModParticleTypes;
import dev.dhyces.lunarnether.server.saveddata.LunarTimeData;

@Mod(LunarNether.MODID)
public class LunarNether {
    public static final String MODID = "lunarnether";
    public static final Logger LOGGER = LoggerFactory.getLogger("LunarNether");

    public LunarNether(IEventBus modEventBus, ModContainer modContainer) {
        BiomeExtensionsMod.init();

        ModFluids.Types.MOD_FLUID_TYPES.register(modEventBus);
        ModFluids.REGISTRY.register(modEventBus);
        ModBlocks.MOD_BLOCKS.register(modEventBus);
        ModItems.MOD_ITEMS.register(modEventBus);
        ModCreativeModTabs.MOD_CREATIVE_TABS.register(modEventBus);
        ModParticleTypes.MOD_PARTICLE_TYPES.register(modEventBus);
        ModBiomeSources.REGISTRY.register(modEventBus);
        ModBiomeModifiers.MOD_BIOME_MODIFIER_TYPES.register(modEventBus);
        ModFeatures.MOD_FEATURES.register(modEventBus);
    }

    @SubscribeEvent
    private void onLevelLoaded(LevelEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel level && level.dimension() == Level.NETHER) {
            LunarTimeData.getOrCreate(level);
            LunarTimeData.currentNether = level;
        }
    }

    @SubscribeEvent
    private void onLevelUnloaded(LevelEvent.Unload event) {
        if (event.getLevel() instanceof ServerLevel level && level.dimension() == Level.NETHER) {
            LunarTimeData.currentNether = null;
        }
    }

    @SubscribeEvent
    private void onLevelTick(LevelTickEvent event) {
        if (event.getLevel() instanceof ServerLevel level && level.dimension() == Level.NETHER) {
            LunarTimeData.getOrCreate(level).update(level.dayTime());
        }
    }
}
