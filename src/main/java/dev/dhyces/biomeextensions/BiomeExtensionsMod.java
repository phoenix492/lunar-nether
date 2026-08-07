package dev.dhyces.biomeextensions;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLLoader;

import dev.dhyces.biomeextensions.extension.BiomeExtensionRegistry;
import dev.dhyces.biomeextensions.impl.ApiContainer;
import dev.dhyces.biomeextensions.registry.ExtendedBiomeRegistry;


public class BiomeExtensionsMod {
    public static final String MODID = "biomeextensions";
    public static ResourceLocation id(String id) {
        return new ResourceLocation(MODID, id);
    }

    public static final ApiContainer API_CONTAINER = new ApiContainer();

    public static void init() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        BiomeExtensionRegistry.init();

        ExtendedBiomeRegistry.init(modBus);

        if (FMLLoader.getDist().isClient()) {
            BiomeExtensionsModClient.init(modBus, forgeBus);
        }
    }
}
