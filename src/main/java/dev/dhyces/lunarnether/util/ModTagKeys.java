package dev.dhyces.lunarnether.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import dev.dhyces.lunarnether.LunarNether;

public class ModTagKeys {
    public static class Blocks {

        public static final TagKey<Block> OUTROCK_BLOB_REPLACEABLES = createTag(LunarNether.MODID, "outrock_blob_replaceables");
        public static final TagKey<Block> MOON_CARVER_REPLACEABLES = createTag(LunarNether.MODID, "moon_carver_replaceables");

        private static TagKey<Block> createTag(String namespace, String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> IS_LUNAR = createTag(LunarNether.MODID, "is_lunar");
        public static final TagKey<Biome> LOWERED_GRAVITY= createTag(LunarNether.MODID, "lowered_gravity");

        private static TagKey<Biome> createTag(String namespace, String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }
}
