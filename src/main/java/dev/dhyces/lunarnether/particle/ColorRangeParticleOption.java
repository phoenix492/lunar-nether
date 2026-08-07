package dev.dhyces.lunarnether.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.util.RandomSource;

import dev.dhyces.lunarnether.util.ColorUtil;
import org.jetbrains.annotations.NotNull;

public class ColorRangeParticleOption implements ParticleOptions {

    public static Codec<ColorRangeParticleOption> codec(ParticleType<ColorRangeParticleOption> type) {
        return RecordCodecBuilder.create(instance ->
                instance.group(
                        ColorUtil.HEX_COLOR.fieldOf("min").forGetter(option -> option.minRgbColor),
                        ColorUtil.HEX_COLOR.fieldOf("max").forGetter(option -> option.maxRgbColor),
                        ColorUtil.ColorSpace.CODEC.optionalFieldOf("color_space", ColorUtil.ColorSpace.RGB).forGetter(option -> option.colorSpace)
                ).apply(instance, (integer, integer2, colorSpace1) -> new ColorRangeParticleOption(type, integer, integer2, colorSpace1)));
    }

    private final ParticleType<ColorRangeParticleOption> particleType;
    private final int minRgbColor;
    private final int maxRgbColor;
    private final ColorUtil.ColorSpace colorSpace;

    public ColorRangeParticleOption(ParticleType<ColorRangeParticleOption> particleType, int minRgbColor, int maxRgbColor, ColorUtil.ColorSpace colorSpace) {
        this.particleType = particleType;
        this.minRgbColor = minRgbColor;
        this.maxRgbColor = maxRgbColor;
        this.colorSpace = colorSpace;
    }

    public int interpolate(RandomSource randomSource) {
        return colorSpace.interpolate(minRgbColor, maxRgbColor, randomSource.nextFloat());
    }

    @Override
    public @NotNull ParticleType<?> getType() {
        return particleType;
    }

}
