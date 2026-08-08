package dev.dhyces.lunarnether.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

import dev.dhyces.lunarnether.LunarNetherClient;
import dev.dhyces.lunarnether.server.saveddata.LunarTimeData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionType.class)
public abstract class DimensionTypeMixin {
    @Shadow
    public abstract OptionalLong fixedTime();

    @Shadow
    public abstract ResourceLocation effectsLocation();

    @Shadow
    @Final
    private ResourceLocation effectsLocation;

    @Mutable
    @Shadow
    @Final
    private OptionalLong fixedTime;

    @Inject(method = "timeOfDay", at = @At(value = "HEAD"), cancellable = true)
    private void getTimeOfDay(long pDayTime, CallbackInfoReturnable<Float> cir) {
        ServerLevel level = LunarTimeData.currentNether;
        if (level != null && (Object) this == level.dimensionType()) {
            if (level.isClientSide()) {
                cir.setReturnValue(LunarTimeData.netherTimeOfDay(LunarNetherClient.netherDayTime));
            } else {
                cir.setReturnValue(LunarTimeData.netherTimeOfDay(LunarTimeData.getOrCreate(level).daytime()));
            }
        }
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void lunarnether$nether_fixed_time_null(OptionalLong fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultraWarm, boolean natural, double coordinateScale, boolean bedWorks, boolean respawnAnchorWorks, int minY, int height, int logicalHeight, TagKey infiniburn, ResourceLocation effectsLocation, float ambientLight, DimensionType.MonsterSettings monsterSettings, CallbackInfo ci) {
        this.fixedTime = OptionalLong.empty();
    }

    @Inject(method = "hasFixedTime", at = @At(value = "HEAD"), cancellable = true)
    private void lunarnether$nether_has_fixed_time(CallbackInfoReturnable<Boolean> cir) {
        if(this.effectsLocation().equals(BuiltinDimensionTypes.NETHER_EFFECTS)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "hasSkyLight", at = @At(value = "HEAD"), cancellable = true)
    private void lunarnether$nether_has_skylight(CallbackInfoReturnable<Boolean> cir) {
        if(this.effectsLocation().equals(BuiltinDimensionTypes.NETHER_EFFECTS)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "hasCeiling", at = @At(value = "HEAD"), cancellable = true)
    private void lunarnether$nether_has_ceiling(CallbackInfoReturnable<Boolean> cir) {
        if(this.effectsLocation().equals(BuiltinDimensionTypes.NETHER_EFFECTS)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "logicalHeight", at = @At(value = "HEAD"), cancellable = true)
    private void lunarnether$nether_logicalheight(CallbackInfoReturnable<Integer> cir) {
        if(this.effectsLocation().equals(BuiltinDimensionTypes.NETHER_EFFECTS)) {
            cir.setReturnValue(255);
        }
    }
}
