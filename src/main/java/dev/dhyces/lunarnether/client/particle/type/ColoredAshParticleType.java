package dev.dhyces.lunarnether.client.particle.type;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.StreamCodec;

import dev.dhyces.lunarnether.client.particle.option.ColorRangeParticleOption;
import org.jetbrains.annotations.NotNull;

public class ColoredAshParticleType extends ParticleType<ColorRangeParticleOption> {

    public ColoredAshParticleType(boolean overrideLimiter) {
        super(overrideLimiter);
    }

    @Override
    public @NotNull MapCodec codec() {
        return null;
    }

    @Override
    public @NotNull StreamCodec streamCodec() {
        return null;
    }
}
