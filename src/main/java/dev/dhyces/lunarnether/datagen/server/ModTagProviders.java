package dev.dhyces.lunarnether.datagen.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.internal.NeoForgeBlockTagsProvider;

import java.util.concurrent.CompletableFuture;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModTagProviders {
    public static class ModBlockTagProvider extends BlockTagsProvider {

        public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, LunarNether.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.LUNAR_STONE.get())
                .add(ModBlocks.LUNAR_STONE_SLAB.get())
                .add(ModBlocks.LUNAR_STONE_STAIRS.get())
                .add(ModBlocks.LUNAR_STONE_WALL.get())
                .add(ModBlocks.POLISHED_LUNAR_STONE.get())
                .add(ModBlocks.POLISHED_LUNAR_STONE_SLAB.get())
                .add(ModBlocks.POLISHED_LUNAR_STONE_STAIRS.get())
                .add(ModBlocks.POLISHED_LUNAR_STONE_WALL.get())
                .add(ModBlocks.CUT_POLISHED_LUNAR_STONE.get())
                .add(ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB.get())
                .add(ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS.get())
                .add(ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL.get())
                .add(ModBlocks.ILMENITE_ORE.get())
                .add(ModBlocks.RAW_ILMENITE_BLOCK.get())
                .add(ModBlocks.CUT_TITANIUM.get())
                .add(ModBlocks.CUT_TITANIUM_STAIRS.get())
                .add(ModBlocks.CUT_TITANIUM_SLAB.get())
                .add(ModBlocks.CUT_TITANIUM_WALL.get())
                .add(ModBlocks.ASTRALITH.get());

            tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.LUNAR_DUST.get());

            tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ASTRALITH.get());

            tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CUT_TITANIUM.get())
                .add(ModBlocks.CUT_TITANIUM_WALL.get())
                .add(ModBlocks.CUT_TITANIUM_SLAB.get())
                .add(ModBlocks.CUT_TITANIUM_STAIRS.get());

            tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ILMENITE_ORE.get())
                .add(ModBlocks.RAW_ILMENITE_BLOCK.get());

            tag(BlockTags.SAND)
                .add(ModBlocks.LUNAR_DUST.get());

            tag(BlockTags.SLABS)
                .add(ModBlocks.LUNAR_STONE_SLAB.get())
                .add(ModBlocks.POLISHED_LUNAR_STONE_SLAB.get())
                .add(ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB.get())
                .add(ModBlocks.CUT_TITANIUM_SLAB.get());

            tag(BlockTags.STAIRS)
                .add(ModBlocks.LUNAR_STONE_STAIRS.get())
                .add(ModBlocks.POLISHED_LUNAR_STONE_STAIRS.get())
                .add(ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS.get())
                .add(ModBlocks.CUT_TITANIUM_STAIRS.get());

            tag(BlockTags.WALLS)
                .add(ModBlocks.LUNAR_STONE_WALL.get())
                .add(ModBlocks.POLISHED_LUNAR_STONE_WALL.get())
                .add(ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL.get())
                .add(ModBlocks.CUT_TITANIUM_WALL.get());

            tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", "ores/titanium")))
                .add(ModBlocks.ILMENITE_ORE.get());

            tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/raw_ilmenite")))
                .add(ModBlocks.RAW_ILMENITE_BLOCK.get());

            tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/titanium")))
                .add(ModBlocks.TITANIUM_BLOCK.get());

            tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks")))
                .addTag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/titanium")))
                .addTag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/raw_ilmenite")));

            tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", "ores")))
                .addTag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", "ores/titanium")));

            tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_carver_replaceables")))
                .add(Blocks.TUFF)
                .add(Blocks.OBSIDIAN)
                .add(ModBlocks.LUNAR_STONE.get())
                .add(ModBlocks.LUNAR_DUST.get())
                .addTag(BlockTags.NETHER_CARVER_REPLACEABLES);

        }
    }

    public static class ModItemTagProvider extends ItemTagsProvider {

        public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTags, LunarNether.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            tag(ItemTags.STAIRS)
                .add(ModItems.LUNAR_STONE_STAIRS.asItem())
                .add(ModItems.POLISHED_LUNAR_STONE_STAIRS.asItem())
                .add(ModItems.CUT_POLISHED_LUNAR_STONE_STAIRS.asItem())
                .add(ModItems.CUT_TITANIUM_STAIRS.asItem());

            tag(ItemTags.SLABS)
                .add(ModItems.LUNAR_STONE_SLAB.asItem())
                .add(ModItems.POLISHED_LUNAR_STONE_SLAB.asItem())
                .add(ModItems.CUT_POLISHED_LUNAR_STONE_SLAB.asItem())
                .add(ModItems.CUT_TITANIUM_SLAB.asItem());

            tag(ItemTags.WALLS)
                .add(ModItems.LUNAR_STONE_WALL.asItem())
                .add(ModItems.POLISHED_LUNAR_STONE_WALL.asItem())
                .add(ModItems.CUT_POLISHED_LUNAR_STONE_WALL.asItem())
                .add(ModItems.CUT_TITANIUM_WALL.asItem());

            tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModItems.LUNAR_STONE.asItem());

            tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(ModItems.LUNAR_STONE.asItem());

            tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.RAW_ILMENITE.asItem())
                .add(ModItems.TITANIUM_INGOT.asItem());

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "ores/titanium")))
                .add(ModItems.ILMENITE_ORE.get());

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/raw_ilmenite")))
                .add(ModItems.RAW_ILMENITE_BLOCK.get());

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/titanium")))
                .add(ModItems.TITANIUM_BLOCK.get());

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks")))
                .addTag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/titanium")))
                .addTag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/raw_ilmenite")));

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "ores")))
                .addTag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "ores/titanium")));

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "ingots/titanium")))
                .add(ModItems.TITANIUM_INGOT.asItem());

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "nuggets/titanium")))
                .add(ModItems.TITANIUM_NUGGET.asItem());

            tag(Tags.Items.ORE_RATES_SINGULAR)
                .add(ModItems.ILMENITE_ORE.asItem());

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "raw_materials/titanium")))
                .add(ModItems.RAW_ILMENITE.asItem());

            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "ingots")))
                .addTag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "ingots/titanium")));
            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "nuggets")))
                .addTag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "nuggets/titanium")));
            tag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "raw_materials")))
                .addTag(TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", "raw_materials/titanium")));

        }
    }

    public static class ModBiomeTagProvider extends BiomeTagsProvider {
        public ModBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, provider, LunarNether.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider provider) {
            tag(BiomeTags.IS_NETHER)
                .addOptional(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "outrocks"));
        }
    }
}
