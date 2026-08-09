package dev.dhyces.lunarnether.datagen.server;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.DistanceTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.advancements.critereon.PlayerPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.google.common.primitives.Doubles;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.registry.ModItems;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    private static final class ModAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
            final AdvancementHolder MOON_LANDING = Advancement.Builder.advancement()
                .parent(AdvancementSubProvider.createPlaceholder("minecraft:nether/root"))
                .display(
                    new ItemStack(ModItems.ASTRALITH.asItem()),
                    Component.translatable("advancements.lunarnnether.moonlanding.title"),
                    Component.translatable("advancements.lunarnnether.moonlanding.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    true
                ).addCriterion(
                    "onLunarNether",
                    PlayerTrigger.TriggerInstance.located(
                        LocationPredicate.Builder.inBiome(
                            provider.lookupOrThrow(Registries.BIOME).getOrThrow(
                                ResourceKey.create(
                                    Registries.BIOME,
                                    ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "outrocks")
                                )
                            )
                        ).setCanSeeSky(true)
                        .setDimension(
                            ResourceKey.create(
                                Registries.DIMENSION,
                                ResourceLocation.withDefaultNamespace("the_nether")
                            )
                        )
                    )
                ).save(consumer, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_landing"), existingFileHelper);

            AdvancementHolder MOON_LANDING_2 = Advancement.Builder.advancement()
                .parent(MOON_LANDING)
                .display(
                    new ItemStack(Items.RABBIT_FOOT),
                    Component.translatable("advancements.lunarnnether.moonlanding2.title"),
                    Component.translatable("advancements.lunarnnether.moonlanding2.description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    true
                ).addCriterion(
                    "bigJumpOutrocks",
                    DistanceTrigger.TriggerInstance.fallFromHeight(
                        EntityPredicate.Builder.entity().effects(
                            MobEffectsPredicate.Builder.effects()
                                .and(
                                    provider.holderOrThrow(
                                        ResourceKey.create(
                                            Registries.MOB_EFFECT,
                                            ResourceLocation.withDefaultNamespace("jump_boost")
                                        )
                                    )
                                )
                        ).located(
                            LocationPredicate.Builder.inBiome(
                                provider.lookupOrThrow(Registries.BIOME).getOrThrow(
                                    ResourceKey.create(
                                        Registries.BIOME,
                                        ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "outrocks")
                                    )
                                )
                            )
                        ),
                        DistancePredicate.vertical(MinMaxBounds.Doubles.atLeast(1)),
                        LocationPredicate.Builder.location()
                    )
                ).save(consumer, ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "moon_landing_2"), existingFileHelper);
        }
    }
}