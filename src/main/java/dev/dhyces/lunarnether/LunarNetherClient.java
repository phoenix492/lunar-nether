package dev.dhyces.lunarnether;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import dev.dhyces.lunarnether.client.dimensionspecialeffects.LunarNetherDimensionEffects;
import dev.dhyces.lunarnether.client.particle.ColoredAshParticle;
import dev.dhyces.lunarnether.registry.ModItems;
import dev.dhyces.lunarnether.registry.ModParticleTypes;

@Mod(value = LunarNether.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = LunarNether.MODID, value = Dist.CLIENT)
public final class LunarNetherClient {


    /**
     * A separate time value for the nether which controls light and sky rendering.
     * Increases 8 times slower than the normal overworld daytime.
     */
    public static long netherDayTime = 0;


    public LunarNetherClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    static void register(IEventBus modBus) {
        modBus.addListener(LunarNetherClient::registerItemProperties);
        modBus.addListener(LunarNetherClient::registerParticles);
        modBus.addListener(LunarNetherClient::netherSky);

    }

    @SubscribeEvent
    private static void registerItemProperties(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.LUNAR_CLOCK.get(), ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_phase"), (pStack, pLevel, pEntity, pSeed) ->
                pLevel == null ? 0 : pLevel.getMoonPhase() / 8f
            );
        });
    }

    private static void registerParticles(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.COLORED_ASH, ColoredAshParticle.Provider::new);
    }

    private static void netherSky(final RegisterDimensionSpecialEffectsEvent event) {
        event.register(BuiltinDimensionTypes.NETHER_EFFECTS, new LunarNetherDimensionEffects());
    }



    public static final int LENGTH_OF_LUNAR_DAY = 24000*8;

    public static double eclipse() {
        double shiftedEclipse = LunarNetherClient.netherDayTime % LENGTH_OF_LUNAR_DAY - 12000;
        return (20d / 1000000000) * (shiftedEclipse * shiftedEclipse);
    }

    public static float skyDarkness(double eclipseParabola) {
        double decimal = Mth.frac(LunarNetherClient.netherDayTime / (float)LENGTH_OF_LUNAR_DAY - 0.25);
        double d1 = 0.5 - Math.cos(decimal * Math.PI) / 2;
        return (float)(decimal * 2 + Math.min(d1, eclipseParabola)) / 3.0F;
    }
}
