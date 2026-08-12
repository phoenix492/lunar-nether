package dev.dhyces.lunarnether;

import net.neoforged.neoforge.common.ModConfigSpec;

import org.apache.commons.lang3.tuple.Pair;

public class LunarNetherConfig {
    public static final LunarNetherConfig SERVER_CONFIG;
    public static final ModConfigSpec SPEC;

    public enum FallDamageReductionMethod {
        NONE,
        SAFE_FALL_DIV,
        DISTANCE_MULT
    }

    public final ModConfigSpec.ConfigValue<Boolean> applyModifiedGravity;
    public final ModConfigSpec.ConfigValue<Double> modifiedGravityMultiplier;
    public final ModConfigSpec.EnumValue<FallDamageReductionMethod> fallDamageReductionMethod;

    static {
        Pair<LunarNetherConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(LunarNetherConfig::new);
        SERVER_CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    private LunarNetherConfig(ModConfigSpec.Builder BUILDER) {
        applyModifiedGravity = BUILDER
            .comment("Whether to apply a multiplier to gravity in biomes tagged #lunarnether:lowered_gravity")
            .define("applyModifiedGravity", true);

        modifiedGravityMultiplier = BUILDER
            .comment("Sets the multiplier applied to the gravity on entities in biomes tagged #lunarnether:lowered_gravity.")
            .comment("Does nothing if applyModifiedGravity is set to false.")
            .comment("0.5 will be half gravity (weaker), 2 would be double gravity (stronger).")
            .define("modifiedGravityMultiplier", 0.4);

        fallDamageReductionMethod = BUILDER
            .comment("What method to use for modifying fall damage in accordance with modified gravity.")
            .comment("NONE: Fall damage is based on amount of blocks fallen, as in vanilla.")
            .comment("SAFE_FALL_DIV: Amount of distance that is considered \"safe\" to fall is divided by modifiedGravityMultiplier. Still increases one to one with blocks fallen past the safe distance, but increases/reduces amount of blocks before fall damage starts.")
            .comment("DISTANCE_MULT: Multiplies the distance the game considers you to have fallen for the purposes of fall damage calculation by modifiedGravityMultiplier. Fall damage increase per block is equal to modifiedGravityMultiplier.")
            .defineEnum("fallDamageReductionMethod", FallDamageReductionMethod.SAFE_FALL_DIV);
    }
}
