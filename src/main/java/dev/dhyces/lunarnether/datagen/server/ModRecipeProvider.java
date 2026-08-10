package dev.dhyces.lunarnether.datagen.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.ModBlocks;
import dev.dhyces.lunarnether.registry.ModItems;
import org.jetbrains.annotations.NotNull;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput, HolderLookup.@NotNull Provider holderLookup) {

        // Items
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.LUNAR_CLOCK.asItem())
            .pattern(" TR")
            .pattern("TLT")
            .pattern(" T ")
            .define('T', ModItems.TITANIUM_INGOT)
            .define('L', ModBlocks.LUNAR_STONE)
            .define('R', Items.REDSTONE)
            .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
            .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ILMENITE, 9).requires(ModBlocks.RAW_ILMENITE_BLOCK).unlockedBy("has_raw_ilmenite", has(ModItems.RAW_ILMENITE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ILMENITE_BLOCK, 1).requires(ModItems.RAW_ILMENITE, 9).unlockedBy("has_raw_ilmenite", has(ModItems.RAW_ILMENITE)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TITANIUM_NUGGET, 9).requires(ModItems.TITANIUM_INGOT).unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TITANIUM_INGOT, 1).requires(ModItems.TITANIUM_NUGGET, 9).unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "titanium_ingot_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TITANIUM_INGOT, 9).requires(ModItems.TITANIUM_BLOCK).unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "titanium_ingot_from_titanium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TITANIUM_BLOCK, 1).requires(ModItems.TITANIUM_INGOT, 9).unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT)).save(recipeOutput);

        // Furnace
        oreSmeltingForMod(recipeOutput, List.of(ModItems.ILMENITE_ORE.get()), RecipeCategory.MISC, Items.IRON_INGOT, 1.0f, 200, "smelting");
        oreSmeltingForMod(recipeOutput, List.of(ModItems.RAW_ILMENITE.get()), RecipeCategory.MISC, Items.IRON_NUGGET, 1.0f, 200, "smelting");
        oreBlastingForMod(recipeOutput, List.of(ModItems.ILMENITE_ORE.get()), RecipeCategory.MISC, ModItems.TITANIUM_INGOT, 1.0f, 100, "blasting");
        oreBlastingForMod(recipeOutput, List.of(ModItems.RAW_ILMENITE.get()), RecipeCategory.MISC, ModItems.TITANIUM_NUGGET, 1.0f, 100, "blasting");
        smeltingResultFromBaseForMod(recipeOutput, Items.GLASS, ModBlocks.LUNAR_DUST);

        // Building Blocks
        polishedBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LUNAR_STONE.asItem(), Ingredient.of(ModBlocks.LUNAR_STONE.asItem())).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        cutBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_POLISHED_LUNAR_STONE.asItem(), Ingredient.of(ModBlocks.POLISHED_LUNAR_STONE.asItem())).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        cutBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_TITANIUM.asItem(), Ingredient.of(ModBlocks.TITANIUM_BLOCK.asItem())).unlockedBy("has_titanium_block", has(ModBlocks.TITANIUM_BLOCK)).save(recipeOutput);

        // Stairs
        stairBuilder(ModBlocks.LUNAR_STONE_STAIRS, Ingredient.of(ModBlocks.LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        stairBuilder(ModBlocks.POLISHED_LUNAR_STONE_STAIRS, Ingredient.of(ModBlocks.POLISHED_LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        stairBuilder(ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS, Ingredient.of(ModBlocks.CUT_POLISHED_LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        stairBuilder(ModBlocks.CUT_TITANIUM_STAIRS, Ingredient.of(ModBlocks.CUT_TITANIUM)).unlockedBy("has_titanium_block", has(ModBlocks.TITANIUM_BLOCK)).save(recipeOutput);

        // Slabs
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUNAR_STONE_SLAB, Ingredient.of(ModBlocks.LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LUNAR_STONE_SLAB, Ingredient.of(ModBlocks.POLISHED_LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB, Ingredient.of(ModBlocks.CUT_POLISHED_LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_TITANIUM_SLAB, Ingredient.of(ModBlocks.CUT_TITANIUM)).unlockedBy("has_titanium_block", has(ModBlocks.TITANIUM_BLOCK)).save(recipeOutput);

        // Walls
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LUNAR_STONE_WALL, Ingredient.of(ModBlocks.LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_LUNAR_STONE_WALL, Ingredient.of(ModBlocks.POLISHED_LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL, Ingredient.of(ModBlocks.CUT_POLISHED_LUNAR_STONE)).unlockedBy("has_lunar_stone", has(ModBlocks.LUNAR_STONE)).save(recipeOutput);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_TITANIUM_WALL, Ingredient.of(ModBlocks.CUT_TITANIUM)).unlockedBy("has_titanium_block", has(ModBlocks.TITANIUM_BLOCK)).save(recipeOutput);

        // Stonecutter Blocks
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.POLISHED_LUNAR_STONE,
            ModBlocks.LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE,
            ModBlocks.LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE,
            ModBlocks.POLISHED_LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_TITANIUM,
            ModBlocks.TITANIUM_BLOCK,
            1
        );

        // Stonecutter Slabs
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.LUNAR_STONE_SLAB,
            ModBlocks.LUNAR_STONE,
            2
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.POLISHED_LUNAR_STONE_SLAB,
            ModBlocks.LUNAR_STONE,
            2
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.POLISHED_LUNAR_STONE_SLAB,
            ModBlocks.POLISHED_LUNAR_STONE,
            2
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB,
            ModBlocks.LUNAR_STONE,
            2
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB,
            ModBlocks.POLISHED_LUNAR_STONE,
            2
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB,
            ModBlocks.CUT_POLISHED_LUNAR_STONE,
            2
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_TITANIUM_SLAB,
            ModBlocks.CUT_TITANIUM,
            2
        );

        // Stonecutter Stairs
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.LUNAR_STONE_STAIRS,
            ModBlocks.LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.POLISHED_LUNAR_STONE_STAIRS,
            ModBlocks.LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.POLISHED_LUNAR_STONE_STAIRS,
            ModBlocks.POLISHED_LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS,
            ModBlocks.LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS,
            ModBlocks.POLISHED_LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_TITANIUM_STAIRS,
            ModBlocks.CUT_TITANIUM,
            1
        );

        // Stonecutter Walls
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.LUNAR_STONE_WALL,
            ModBlocks.LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.POLISHED_LUNAR_STONE_WALL,
            ModBlocks.LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.POLISHED_LUNAR_STONE_WALL,
            ModBlocks.POLISHED_LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL,
            ModBlocks.LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL,
            ModBlocks.POLISHED_LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL,
            ModBlocks.CUT_POLISHED_LUNAR_STONE,
            1
        );
        stonecutterResultFromBaseForMod(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.CUT_TITANIUM_WALL,
            ModBlocks.CUT_TITANIUM,
            1
        );
    }

    protected static void stonecutterResultFromBaseForMod(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike material, int resultCount) {
        String conversionRecipeName = getConversionRecipeName(result, material);
        SingleItemRecipeBuilder
            .stonecutting(Ingredient.of(material), category, result, resultCount)
            .unlockedBy(getHasName(material), has(material))
            .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "stonecutting/" + conversionRecipeName));

    }

    protected static void oreSmeltingForMod(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCookingForMod(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlastingForMod(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCookingForMod(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCookingForMod(RecipeOutput recipeOutput, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String suffix) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory).group(group).unlockedBy(getHasName(itemlike), has(itemlike)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "smelting/" + getItemName(result) + suffix + "_" + getItemName(itemlike)));
        }
    }

    protected static void smeltingResultFromBaseForMod(RecipeOutput recipeOutput, ItemLike result, ItemLike ingredient) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, result, 0.1F, 200).unlockedBy(getHasName(ingredient), has(ingredient)).save(recipeOutput, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "smelting/" + getItemName(result) + "_from" + "_" + getItemName(ingredient)));
    }

}
