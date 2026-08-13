package dev.dhyces.lunarnether.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.dhyces.lunarnether.config.LunarNetherServerConfig;
import dev.dhyces.lunarnether.util.ModTagKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public abstract class GravityMixins {
    @Mixin(Entity.class)
    public static abstract class EntityMixin {
        @Shadow
        public abstract Level level();

        @Shadow
        public abstract BlockPos blockPosition();

        @ModifyReturnValue(method = "getGravity", at = @At("RETURN"))
        public double lunarnether$modifyGravity(double original) {
            if (this.level().getBiome(this.blockPosition()).is(ModTagKeys.Biomes.LOWERED_GRAVITY)) {
                return original * LunarNetherServerConfig.SERVER_CONFIG.modifiedGravityMultiplier.get();
            }
            return original;
        }
    }

    @Mixin(LivingEntity.class)
    public static abstract class LivingEntityMixin extends Entity {
        private LivingEntityMixin(EntityType<?> entityType, Level level) {
            super(entityType, level);
        }

        @ModifyVariable(method = "calculateFallDamage", at = @At("HEAD"), ordinal = 0)
        public float lunarnether$modifyFallDistance(float fallDistance) {
            if (LunarNetherServerConfig.SERVER_CONFIG.applyModifiedGravity.get() &&
                LunarNetherServerConfig.SERVER_CONFIG.fallDamageReductionMethod.get() == LunarNetherServerConfig.FallDamageReductionMethod.DISTANCE_MULT &&
                this.level().getBiome(this.blockPosition()).is(ModTagKeys.Biomes.LOWERED_GRAVITY)
            ) {
                return (float) (fallDistance * LunarNetherServerConfig.SERVER_CONFIG.modifiedGravityMultiplier.get());
            }
            else {
                return fallDistance;
            }
        }

        @ModifyVariable(method = "calculateFallDamage", at = @At("STORE"), ordinal = 2)
        public float lunarnether$modifySafeFallDistance(float f) {
            if (LunarNetherServerConfig.SERVER_CONFIG.applyModifiedGravity.get() &&
                LunarNetherServerConfig.SERVER_CONFIG.fallDamageReductionMethod.get() ==  LunarNetherServerConfig.FallDamageReductionMethod.SAFE_FALL_DIV &&
                this.level().getBiome(this.blockPosition()).is(ModTagKeys.Biomes.LOWERED_GRAVITY)
            ) {
                return (float) (f / LunarNetherServerConfig.SERVER_CONFIG.modifiedGravityMultiplier.get());
            } else {
                return f;
            }
        }
    }
}
