package dev.dhyces.lunarnether.datagen.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModItems;
import org.jetbrains.annotations.NotNull;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    public ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        // The contents of our DeferredRegister.
        return ModBlocks.MOD_BLOCKS.getEntries()
            .stream()
            // Cast to Block here, otherwise it will be a ? extends Block and Java will complain.
            .map(e -> (Block) e.value())
            .toList();
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.LUNAR_STONE.get());
        dropSelf(ModBlocks.LUNAR_STONE_SLAB.get());
        dropSelf(ModBlocks.LUNAR_STONE_STAIRS.get());
        dropSelf(ModBlocks.LUNAR_STONE_WALL.get());

        dropSelf(ModBlocks.POLISHED_LUNAR_STONE.get());
        dropSelf(ModBlocks.POLISHED_LUNAR_STONE_STAIRS.get());
        dropSelf(ModBlocks.POLISHED_LUNAR_STONE_WALL.get());
        dropSelf(ModBlocks.POLISHED_LUNAR_STONE_SLAB.get());

        dropSelf(ModBlocks.CUT_POLISHED_LUNAR_STONE.get());
        dropSelf(ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB.get());
        dropSelf(ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS.get());
        dropSelf(ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL.get());

        dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        dropSelf(ModBlocks.CUT_TITANIUM.get());
        dropSelf(ModBlocks.CUT_TITANIUM_STAIRS.get());
        dropSelf(ModBlocks.CUT_TITANIUM_SLAB.get());
        dropSelf(ModBlocks.CUT_TITANIUM_WALL.get());

        dropSelf(ModBlocks.RAW_ILMENITE_BLOCK.get());
        dropSelf(ModBlocks.LUNAR_DUST.get());
        dropSelf(ModBlocks.ASTRALITH.get());

        add(ModBlocks.ILMENITE_ORE.get(), createOreDrop(ModBlocks.ILMENITE_ORE.get(), ModItems.RAW_ILMENITE.asItem()));
    }
}
