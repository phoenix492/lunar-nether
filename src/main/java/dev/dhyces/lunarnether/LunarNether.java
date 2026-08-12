package dev.dhyces.lunarnether;

import net.minecraft.util.Mth;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModCreativeModTabs;
import dev.dhyces.lunarnether.registry.ModFeatures;
import dev.dhyces.lunarnether.registry.ModFluids;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.registry.ModParticleTypes;

@Mod(LunarNether.MODID)

public class LunarNether {
    public static final String MODID = "lunarnether";
    public static final Logger LOGGER = LoggerFactory.getLogger("LunarNether");

    public LunarNether(IEventBus modEventBus, ModContainer modContainer) {
        ModFluids.Types.MOD_FLUID_TYPES.register(modEventBus);
        ModFluids.Fluids.MOD_FLUIDS.register(modEventBus);
        ModBlocks.MOD_BLOCKS.register(modEventBus);
        ModItems.MOD_ITEMS.register(modEventBus);
        ModCreativeModTabs.MOD_CREATIVE_TABS.register(modEventBus);
        ModParticleTypes.MOD_PARTICLE_TYPES.register(modEventBus);
        ModFeatures.MOD_FEATURES.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.SERVER, LunarNetherConfig.SPEC);
    }

    /**
     * A separate time calculation for the nether which controls light and sky rendering.
     * Increases 8 times slower than the normal overworld daytime.
     */
    public static float netherTimeOfDay(long daytime) {
        double decimal = Mth.frac(daytime / (24000.0 * 8) - 0.25);
        double d1 = 0.5 - Math.cos(decimal * Math.PI) / 2;
        return (float)(decimal * 2 + d1) / 3.0F;
    }

}
