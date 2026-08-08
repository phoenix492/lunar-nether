package dev.dhyces.lunarnether.datagen.client;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

import dev.dhyces.lunarnether.registry.ModParticleTypes;

public class ModParticleDescriptionProvider extends ParticleDescriptionProvider {
    public ModParticleDescriptionProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, existingFileHelper);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(
            ModParticleTypes.COLORED_ASH.get(),
            ResourceLocation.withDefaultNamespace("generic_0")
        );
    }
}
