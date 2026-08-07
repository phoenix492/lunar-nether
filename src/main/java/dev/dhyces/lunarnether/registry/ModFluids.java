package dev.dhyces.lunarnether.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import dev.dhyces.lunarnether.LunarNether;

public class ModFluids {

    public static class Types {
        public static final DeferredRegister<FluidType> MOD_FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, LunarNether.MODID);

        public static final DeferredHolder<FluidType, FluidType> MOLTEN_TITANIUM = MOD_FLUID_TYPES.register(
            "molten_titanium",
            () -> new FluidType(
                FluidType.Properties.create()
                    .temperature(1300)
                    .lightLevel(10)
                    .canSwim(false)
                    .canDrown(false)
                    .pathType(PathType.LAVA)
            )
        );
    }

    public static class Fluids {
        public static final DeferredRegister<Fluid> MOD_FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, LunarNether.MODID);

    }
}
