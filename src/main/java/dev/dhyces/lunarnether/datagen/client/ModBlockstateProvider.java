package dev.dhyces.lunarnether.datagen.client;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.ModBlocks;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LunarNether.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(
            ModBlocks.ASTRALITH.get(),
            ConfiguredModel.builder()
                .modelFile(new ModelFile.UncheckedModelFile(modLoc("block/astralith_1"))).nextModel()
                .modelFile(new ModelFile.UncheckedModelFile(modLoc("block/astralith_2"))).nextModel()
                .modelFile(new ModelFile.UncheckedModelFile(modLoc("block/astralith_3"))).nextModel()
                .modelFile(new ModelFile.UncheckedModelFile(modLoc("block/astralith_4")))
                .build()
        );

        blockWithItem(ModBlocks.LUNAR_STONE);
        slabBlock(ModBlocks.LUNAR_STONE_SLAB.get(), blockTexture(ModBlocks.LUNAR_STONE.get()), blockTexture(ModBlocks.LUNAR_STONE.get()));
        stairsBlock(ModBlocks.LUNAR_STONE_STAIRS.get(), blockTexture(ModBlocks.LUNAR_STONE.get()));
        wallBlock(ModBlocks.LUNAR_STONE_WALL.get(), blockTexture(ModBlocks.LUNAR_STONE.get()));

        blockWithItem(ModBlocks.POLISHED_LUNAR_STONE);
        slabBlock(ModBlocks.POLISHED_LUNAR_STONE_SLAB.get(), blockTexture(ModBlocks.POLISHED_LUNAR_STONE.get()), blockTexture(ModBlocks.POLISHED_LUNAR_STONE.get()));
        stairsBlock(ModBlocks.POLISHED_LUNAR_STONE_STAIRS.get(), blockTexture(ModBlocks.POLISHED_LUNAR_STONE.get()));
        wallBlock(ModBlocks.POLISHED_LUNAR_STONE_WALL.get(), blockTexture(ModBlocks.POLISHED_LUNAR_STONE.get()));

        blockWithItem(ModBlocks.CUT_POLISHED_LUNAR_STONE);
        slabBlock(ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB.get(), blockTexture(ModBlocks.CUT_POLISHED_LUNAR_STONE.get()), blockTexture(ModBlocks.CUT_POLISHED_LUNAR_STONE.get()));
        stairsBlock(ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS.get(), blockTexture(ModBlocks.CUT_POLISHED_LUNAR_STONE.get()));
        wallBlock(ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL.get(), blockTexture(ModBlocks.CUT_POLISHED_LUNAR_STONE.get()));

        blockWithItem(ModBlocks.CUT_TITANIUM);
        slabBlock(ModBlocks.CUT_TITANIUM_SLAB.get(), blockTexture(ModBlocks.CUT_TITANIUM.get()), blockTexture(ModBlocks.CUT_TITANIUM.get()));
        stairsBlock(ModBlocks.CUT_TITANIUM_STAIRS.get(), blockTexture(ModBlocks.CUT_TITANIUM.get()));
        wallBlock(ModBlocks.CUT_TITANIUM_WALL.get(), blockTexture(ModBlocks.CUT_TITANIUM.get()));


        blockWithItem(ModBlocks.ILMENITE_ORE);
        blockWithItem(ModBlocks.LUNAR_DUST);
        blockWithItem(ModBlocks.RAW_ILMENITE_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

}
