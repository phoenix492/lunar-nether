package dev.dhyces.lunarnether.datagen.client;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, LunarNether.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("astralith", ResourceLocation.parse("lunarnether:block/astralith_1"));

        simpleBlockItem(ModBlocks.LUNAR_STONE.get());
        simpleBlockItem(ModBlocks.LUNAR_STONE_SLAB.get());
        simpleBlockItem(ModBlocks.LUNAR_STONE_STAIRS.get());
        wallItem(ModBlocks.LUNAR_STONE_WALL, ModBlocks.LUNAR_STONE);

        simpleBlockItem(ModBlocks.POLISHED_LUNAR_STONE.get());
        simpleBlockItem(ModBlocks.POLISHED_LUNAR_STONE_SLAB.get());
        simpleBlockItem(ModBlocks.POLISHED_LUNAR_STONE_STAIRS.get());
        wallItem(ModBlocks.POLISHED_LUNAR_STONE_WALL, ModBlocks.POLISHED_LUNAR_STONE);

        simpleBlockItem(ModBlocks.CUT_POLISHED_LUNAR_STONE.get());
        simpleBlockItem(ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB.get());
        simpleBlockItem(ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS.get());
        wallItem(ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL, ModBlocks.CUT_POLISHED_LUNAR_STONE);

        simpleBlockItem(ModBlocks.CUT_TITANIUM.get());
        simpleBlockItem(ModBlocks.CUT_TITANIUM_SLAB.get());
        simpleBlockItem(ModBlocks.CUT_TITANIUM_STAIRS.get());
        wallItem(ModBlocks.CUT_TITANIUM_WALL, ModBlocks.CUT_TITANIUM);


        basicItem(ModItems.RAW_ILMENITE.asItem());
        basicItem(ModItems.TITANIUM_INGOT.asItem());
        basicItem(ModItems.TITANIUM_NUGGET.asItem());

        withExistingParent("moon_clock_0", "minecraft:item/generated").texture("layer0", "lunarnether:item/moon_clock_0");
        withExistingParent("moon_clock_1", "minecraft:item/generated").texture("layer0", "lunarnether:item/moon_clock_1");
        withExistingParent("moon_clock_2", "minecraft:item/generated").texture("layer0", "lunarnether:item/moon_clock_2");
        withExistingParent("moon_clock_3", "minecraft:item/generated").texture("layer0", "lunarnether:item/moon_clock_3");
        withExistingParent("moon_clock_4", "minecraft:item/generated").texture("layer0", "lunarnether:item/moon_clock_4");
        withExistingParent("moon_clock_5", "minecraft:item/generated").texture("layer0", "lunarnether:item/moon_clock_5");
        withExistingParent("moon_clock_6", "minecraft:item/generated").texture("layer0", "lunarnether:item/moon_clock_6");
        withExistingParent("moon_clock_7", "minecraft:item/generated").texture("layer0", "lunarnether:item/moon_clock_7");

        withExistingParent("lunar_clock", "minecraft:item/generated").texture("layer0", modLoc("item/moon_clock_0"))
            .override().model(new ModelFile.UncheckedModelFile("lunarnether:item/moon_clock_7")).predicate(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_phase"), 0.125f).end()
            .override().model(new ModelFile.UncheckedModelFile("lunarnether:item/moon_clock_6")).predicate(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_phase"), 0.25f).end()
            .override().model(new ModelFile.UncheckedModelFile("lunarnether:item/moon_clock_5")).predicate(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_phase"), 0.375f).end()
            .override().model(new ModelFile.UncheckedModelFile("lunarnether:item/moon_clock_4")).predicate(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_phase"), 0.5f).end()
            .override().model(new ModelFile.UncheckedModelFile("lunarnether:item/moon_clock_3")).predicate(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_phase"), 0.625f).end()
            .override().model(new ModelFile.UncheckedModelFile("lunarnether:item/moon_clock_2")).predicate(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_phase"), 0.75f).end()
            .override().model(new ModelFile.UncheckedModelFile("lunarnether:item/moon_clock_1")).predicate(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_phase"), 0.875f).end();

    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
            .texture(
                "wall",
                ResourceLocation.fromNamespaceAndPath(
                    LunarNether.MODID,
                    "block/" + baseBlock.getId().getPath()
                )
            );
    }
}
