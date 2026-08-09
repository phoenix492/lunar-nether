package dev.dhyces.lunarnether.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import dev.dhyces.lunarnether.LunarNether;

public class ModTagKeys {
    public static class Blocks {

        public static final TagKey<Block> OUTROCK_BLOB_REPLACEABLES = createTag(LunarNether.MODID, "outrock_blob_replaceables");

        private static TagKey<Block> createTag(String namespace, String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }
}
