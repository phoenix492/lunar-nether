package dev.dhyces.lunarnether.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryObject;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.particle.ColorRangeParticleOption;
import org.jetbrains.annotations.NotNull;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> MOD_PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, LunarNether.MODID);

    public static final DeferredHolder<ParticleType<?>, ParticleType<ColorRangeParticleOption>> COLORED_ASH = MOD_PARTICLE_TYPES.register(
        "colored_ash",
        () -> new ParticleType<>(false) {
                @Override
                public @NotNull MapCodec<ColorRangeParticleOption> codec() {
                    return ColorRangeParticleOption.codec(this);
                }

            @Override
            public StreamCodec<? super RegistryFriendlyByteBuf, ColorRangeParticleOption> streamCodec() {
                return null;
            }
        }
    );
}
