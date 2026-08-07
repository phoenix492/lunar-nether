package dev.dhyces.biomeextensions;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import dev.dhyces.biomeextensions.extension.BiomeExtension;

public class RegistryHelper {
    public static ResourceKey<BiomeExtension> registryKey(ResourceLocation elementId) {
        return ResourceKey.create(ApiAccess.EXTENSION_REGISTRY_KEY, elementId);
    }
}
