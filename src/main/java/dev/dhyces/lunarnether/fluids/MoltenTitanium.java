package dev.dhyces.lunarnether.fluids;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

public class MoltenTitanium extends LiquidBlock {
    public MoltenTitanium(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public @NotNull Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }
}

