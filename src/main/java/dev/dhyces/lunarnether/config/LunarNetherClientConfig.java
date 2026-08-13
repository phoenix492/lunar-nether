package dev.dhyces.lunarnether.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import org.apache.commons.lang3.tuple.Pair;

public class LunarNetherClientConfig {
    public static final LunarNetherClientConfig CLIENT_CONFIG;
    public static final ModConfigSpec SPEC;

    public final ModConfigSpec.ConfigValue<Boolean> skyboxStartOverrideEnabled;
    public final ModConfigSpec.ConfigValue<Integer> skyboxStart;

    static {
        Pair<LunarNetherClientConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(LunarNetherClientConfig::new);
        CLIENT_CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    private LunarNetherClientConfig(ModConfigSpec.Builder BUILDER) {
        skyboxStartOverrideEnabled = BUILDER
            .comment("Whether to override the start height for Lunar Nether's skybox with the below value.")
            .comment("If set to false, Lunar Nether will instead pick its own start height based on presence or lack of other terrain mods.")
            .define("skyboxStartOverrideEnabled", false);

        skyboxStart = BUILDER
            .comment("What Y value to cease rendering the nether's fog and begin rendering Lunar Nether's own skybox.")
            .comment("Has no effect if skyboxStartOverrideEnabled is set to false")
            .define("skyboxStart", 128);

    }
}
