package dev.dhyces.lunarnether.datagen.client;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.block.TitaniumBulbBlock;
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
        doorBlockWithRenderType(ModBlocks.TITANIUM_DOOR.get(), ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "block/titanium_door_bottom"), ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "block/titanium_door_top"), "cutout_mipped");
        trapdoorBlockWithRenderType(ModBlocks.TITANIUM_TRAPDOOR.get(), ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "block/titanium_trapdoor"), true, "cutout_mipped");
        simpleBlockWithItem(ModBlocks.TITANIUM_GRATE.get(), new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "block/titanium_grate")));

        simpleBlockItem(ModBlocks.TITANIUM_TRAPDOOR.get(), new ModelFile.UncheckedModelFile("lunarnether:block/" + ModBlocks.TITANIUM_TRAPDOOR.getId().getPath() + "_bottom"));
        simpleBlockItem(ModBlocks.TITANIUM_BULB.get(), new ModelFile.UncheckedModelFile(modLoc("block/titanium_bulb")));

        blockWithItem(ModBlocks.CHISELED_TITANIUM);

        getVariantBuilder(ModBlocks.TITANIUM_BULB.get()).forAllStates(state -> {
            if (state.getValue(TitaniumBulbBlock.LIT)) {
                if (state.getValue(TitaniumBulbBlock.POWERED)) {
                    return new ConfiguredModel[] {new ConfiguredModel(models().cubeAll("titanium_bulb_lit_powered", ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "block/titanium_bulb_lit_powered")))};
                } else {
                    return new ConfiguredModel[] {new ConfiguredModel(models().cubeAll("titanium_bulb_lit", ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "block/titanium_bulb_lit")))};

                }
            } else {
                if (state.getValue(TitaniumBulbBlock.POWERED)) {
                    return new ConfiguredModel[] {new ConfiguredModel(models().cubeAll("titanium_bulb_powered", ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "block/titanium_bulb_powered")))};
                } else {
                    return new ConfiguredModel[] {new ConfiguredModel(models().cubeAll("titanium_bulb", ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "block/titanium_bulb")))};
                }
            }
        });


        blockWithItem(ModBlocks.ILMENITE_ORE);
        blockWithItem(ModBlocks.LUNAR_DUST);
        blockWithItem(ModBlocks.RAW_ILMENITE_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

}
