package dev.dhyces.biomeextensions.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import dev.dhyces.biomeextensions.ApiAccess;
import dev.dhyces.biomeextensions.extension.BiomeExtension;

public class ExtendedBiomeRegistry {
    public static void init(IEventBus modBus) {
        modBus.addListener(ExtendedBiomeRegistry::createRegistry);
    }

    private static void createRegistry(final DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(ApiAccess.EXTENSION_REGISTRY_KEY, BiomeExtension.CODEC, BiomeExtension.CODEC);
    }
}
