package dev.dhyces.lunarnether.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import dev.dhyces.lunarnether.LunarNether;

public class ModCreativeModTabs { 
    public static final DeferredRegister<CreativeModeTab> MOD_CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LunarNether.MODID);

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LUNAR_NETHER_TAB = MOD_CREATIVE_TABS.register(
            "moon_tab",
            () -> CreativeModeTab.builder()
                .icon(()-> new ItemStack(ModItems.LUNAR_STONE.get()))
                .title(Component.translatable("creativetab.lunarnether.main"))
                .displayItems((pParameters, pOutput) -> ModItems.MOD_ITEMS.getEntries().forEach((item) -> pOutput.accept(item.get())))
                .build()
        );
}