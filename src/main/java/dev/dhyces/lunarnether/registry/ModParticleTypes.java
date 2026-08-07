package dev.dhyces.lunarnether.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.client.particle.type.ColoredAshParticleType;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> MOD_PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, LunarNether.MODID);

    public static final DeferredHolder<ParticleType<?>, ColoredAshParticleType> COLORED_ASH = MOD_PARTICLE_TYPES.register(
        "colored_ash",
        () -> new ColoredAshParticleType(false)
    );
}
