package dev.dhyces.lunarnether.util;

import net.neoforged.fml.ModList;

import dev.dhyces.lunarnether.config.LunarNetherClientConfig;

public class ClientCompatData {
    public static boolean STELLAR_VIEW_LOADED = false;
    public static boolean TRIMMED_LOADED = false;
    public static boolean INCENDIUM_LOADED = false;

    public static final int SURFACE_START_HEIGHT_VANILLA = 128;
    public static final int SURFACE_START_HEIGHT_INCENDIUM = 187;


    public static void init() {
        STELLAR_VIEW_LOADED = ModList.get().isLoaded("stellarview");
    }

    public static int getSkyboxStartHeight() {
        /*
        if (LunarNetherClientConfig.CLIENT_CONFIG.skyboxStartOverrideEnabled.get()) {
            return LunarNetherClientConfig.CLIENT_CONFIG.skyboxStart.get();
        }
         */

        return SURFACE_START_HEIGHT_VANILLA;
    }
}
