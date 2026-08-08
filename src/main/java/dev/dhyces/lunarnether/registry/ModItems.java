package dev.dhyces.lunarnether.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import dev.dhyces.lunarnether.LunarNether;

public class ModItems {
    public static final DeferredRegister.Items MOD_ITEMS = DeferredRegister.createItems(LunarNether.MODID);

    public static final DeferredItem<Item> LUNAR_DUST = registerBlockItem("lunar_dust", ModBlocks.LUNAR_DUST);

    public static final DeferredItem<Item> LUNAR_STONE = registerBlockItem("lunar_stone", ModBlocks.LUNAR_STONE);
    public static final DeferredItem<Item> LUNAR_STONE_STAIRS = registerBlockItem("lunar_stone_stairs", ModBlocks.LUNAR_STONE_STAIRS::get);
    public static final DeferredItem<Item> LUNAR_STONE_SLAB = registerBlockItem("lunar_stone_slab", ModBlocks.LUNAR_STONE_SLAB::get);
    public static final DeferredItem<Item> LUNAR_STONE_WALL = registerBlockItem("lunar_stone_wall", ModBlocks.LUNAR_STONE_WALL::get);

    public static final DeferredItem<Item> POLISHED_LUNAR_STONE = registerBlockItem("polished_lunar_stone", ModBlocks.POLISHED_LUNAR_STONE);
    public static final DeferredItem<Item> POLISHED_LUNAR_STONE_STAIRS = registerBlockItem("polished_lunar_stone_stairs", ModBlocks.POLISHED_LUNAR_STONE_STAIRS::get);
    public static final DeferredItem<Item> POLISHED_LUNAR_STONE_SLAB = registerBlockItem("polished_lunar_stone_slab", ModBlocks.POLISHED_LUNAR_STONE_SLAB::get);
    public static final DeferredItem<Item> POLISHED_LUNAR_STONE_WALL = registerBlockItem("polished_lunar_stone_wall", ModBlocks.POLISHED_LUNAR_STONE_WALL::get);

    public static final DeferredItem<Item> CUT_POLISHED_LUNAR_STONE = registerBlockItem("cut_polished_lunar_stone", ModBlocks.CUT_POLISHED_LUNAR_STONE);
    public static final DeferredItem<Item> CUT_POLISHED_LUNAR_STONE_STAIRS = registerBlockItem("cut_polished_lunar_stone_stairs", ModBlocks.CUT_POLISHED_LUNAR_STONE_STAIRS::get);
    public static final DeferredItem<Item> CUT_POLISHED_LUNAR_STONE_SLAB = registerBlockItem("cut_polished_lunar_stone_slab", ModBlocks.CUT_POLISHED_LUNAR_STONE_SLAB::get);
    public static final DeferredItem<Item> CUT_POLISHED_LUNAR_STONE_WALL = registerBlockItem("cut_polished_lunar_stone_wall", ModBlocks.CUT_POLISHED_LUNAR_STONE_WALL::get);

    public static final DeferredItem<Item> ILMENITE_ORE = registerBlockItem("ilmenite_ore", ModBlocks.ILMENITE_ORE);
    public static final DeferredItem<Item> RAW_ILMENITE_BLOCK = registerBlockItem("raw_ilmenite_block", ModBlocks.RAW_ILMENITE_BLOCK);

    public static final DeferredItem<Item> TITANIUM_BLOCK = registerBlockItem("titanium_block", ModBlocks.TITANIUM_BLOCK);

    public static final DeferredItem<Item> CUT_TITANIUM = registerBlockItem("cut_titanium", ModBlocks.CUT_TITANIUM);
    public static final DeferredItem<Item> CUT_TITANIUM_STAIRS = registerBlockItem("cut_titanium_stairs", ModBlocks.CUT_TITANIUM_STAIRS::get);
    public static final DeferredItem<Item> CUT_TITANIUM_SLAB = registerBlockItem("cut_titanium_slab", ModBlocks.CUT_TITANIUM_SLAB::get);

    public static final DeferredItem<Item> CUT_TITANIUM_WALL = registerBlockItem("cut_titanium_wall", ModBlocks.CUT_TITANIUM_WALL::get);

    public static final DeferredItem<Item> RAW_ILMENITE = MOD_ITEMS.register("raw_ilmenite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_INGOT = MOD_ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_NUGGET = MOD_ITEMS.register("titanium_nugget", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ASTRALITH = registerBlockItem("astralith", ModBlocks.ASTRALITH);

    public static final DeferredItem<Item> LUNAR_CLOCK = MOD_ITEMS.register("lunar_clock", () -> new Item(new Item.Properties()));

    //public static final DeferredItem<Item> MOLTEN_TITANIUM_BUCKET = MOD_ITEMS.register("molten_titanium_bucket", () -> new BucketItem(ModFluids.MOLTEN_TITANIUM, new Item.Properties()));

    private static DeferredItem<Item> registerBlockItem(String id, Supplier<Block> blockSupplier) {
        return MOD_ITEMS.register(id, () -> new BlockItem(blockSupplier.get(), new Item.Properties()));
    }
}
