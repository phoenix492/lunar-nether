package dev.dhyces.lunarnether.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import dev.dhyces.lunarnether.LunarNether;

public class ModBlocks {
    public static final DeferredRegister.Blocks MOD_BLOCKS = DeferredRegister.createBlocks(LunarNether.MODID);

    //Moondust
    public static final DeferredBlock<Block> LUNAR_DUST = MOD_BLOCKS.register(
        "lunar_dust",
        () -> new Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.CLAY)
                .strength(0.5f)
                .sound(SoundType.SAND)
        )
    );
    
    //Moonstone
    public static final DeferredBlock<Block> LUNAR_STONE = MOD_BLOCKS.register(
        "lunar_stone",
        () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<StairBlock> LUNAR_STONE_STAIRS = MOD_BLOCKS.register(
        "lunar_stone_stairs",
        () -> new StairBlock(
            LUNAR_STONE.get().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<SlabBlock> LUNAR_STONE_SLAB = MOD_BLOCKS.register(
        "lunar_stone_slab",
        () -> new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<WallBlock> LUNAR_STONE_WALL = MOD_BLOCKS.register(
        "lunar_stone_wall",
        () -> new WallBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );

    //Polished Moonstone
    public static final DeferredBlock<Block> POLISHED_LUNAR_STONE = MOD_BLOCKS.register(
        "polished_lunar_stone",
        () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<StairBlock> POLISHED_LUNAR_STONE_STAIRS = MOD_BLOCKS.register(
        "polished_lunar_stone_stairs",
        () -> new StairBlock(
            POLISHED_LUNAR_STONE.get().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<SlabBlock> POLISHED_LUNAR_STONE_SLAB = MOD_BLOCKS.register(
        "polished_lunar_stone_slab",
        () -> new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<WallBlock> POLISHED_LUNAR_STONE_WALL = MOD_BLOCKS.register(
        "polished_lunar_stone_wall",
        () -> new WallBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    
    //Cut Polished Moonstone
    public static final DeferredBlock<Block> CUT_POLISHED_LUNAR_STONE = MOD_BLOCKS.register(
        "cut_polished_lunar_stone",
        () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<StairBlock> CUT_POLISHED_LUNAR_STONE_STAIRS = MOD_BLOCKS.register(
        "cut_polished_lunar_stone_stairs",
        () -> new StairBlock(
            CUT_POLISHED_LUNAR_STONE.get().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<SlabBlock> CUT_POLISHED_LUNAR_STONE_SLAB = MOD_BLOCKS.register(
        "cut_polished_lunar_stone_slab",
        () -> new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );
    public static final DeferredBlock<WallBlock> CUT_POLISHED_LUNAR_STONE_WALL = MOD_BLOCKS.register(
        "cut_polished_lunar_stone_wall",
        () -> new WallBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                .strength(1.6F, 8.0F)
                .mapColor(MapColor.QUARTZ)
        )
    );

    //Ilmenite
    public static final DeferredBlock<Block> ILMENITE_ORE = MOD_BLOCKS.register(
        "ilmenite_ore",
        () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)
                .mapColor(MapColor.STONE)
        )
    );
    public static final DeferredBlock<Block> RAW_ILMENITE_BLOCK = MOD_BLOCKS.register(
        "raw_ilmenite_block",
        () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)
                .strength(2.0F, 16.0F)
        )
    );
    //Titanium
    public static final DeferredBlock<Block> TITANIUM_BLOCK = MOD_BLOCKS.register(
        "titanium_block",
        () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)
                .strength(3.0F, 16.0F)
                .mapColor(MapColor.METAL)
        )
    );

    //Cut Titanium
    public static final DeferredBlock<Block> CUT_TITANIUM = MOD_BLOCKS.register(
        "cut_titanium",
        () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)
                .strength(3.0F, 16.0F)
                .mapColor(MapColor.METAL)
        )
    );
    public static final DeferredBlock<StairBlock> CUT_TITANIUM_STAIRS = MOD_BLOCKS.register(
        "cut_titanium_stairs",
        () -> new StairBlock(
            CUT_TITANIUM.get().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)
                .strength(3.0F, 16.0F)
                .mapColor(MapColor.METAL)
        )
    );
    public static final DeferredBlock<SlabBlock> CUT_TITANIUM_SLAB = MOD_BLOCKS.register(
        "cut_titanium_slab",
        () -> new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)
                .strength(3.0F, 16.0F)
                .mapColor(MapColor.METAL)
        )
    );

    public static final DeferredBlock<WallBlock> CUT_TITANIUM_WALL = MOD_BLOCKS.register(
        "cut_titanium_wall",
        () -> new WallBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)
                .strength(3.0F, 16.0F)
                .mapColor(MapColor.METAL)
        )
    );

    //Astralith
    public static final DeferredBlock<Block> ASTRALITH = MOD_BLOCKS.register(
        "astralith",
        () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS)
                .strength(30.0F, 600.0F)
                .mapColor(MapColor.TERRACOTTA_CYAN)
        )
    );
    /*
    public static final DeferredBlock<LiquidBlock> MOLTEN_TITANIUM = MOD_BLOCKS.register(
        "molten_titanium",
        () -> new LiquidBlock(
            ModFluids.Fluids.MOLTEN_TITANIUM_FLOWING.get(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA)
        )
    );
     */
}
