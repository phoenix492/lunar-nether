package dev.dhyces.lunarnether.datagen.client;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import dev.dhyces.lunarnether.LunarNether;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper)  {
        super(packOutput, LunarNether.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        cubeAll("astralith_1", modLoc("block/astralith_1"));
        cubeAll("astralith_2", modLoc("block/astralith_2"));
        cubeAll("astralith_3", modLoc("block/astralith_3"));
        cubeAll("astralith_4", modLoc("block/astralith_4"));
    }
}
