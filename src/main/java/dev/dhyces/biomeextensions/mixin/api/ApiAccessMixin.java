package dev.dhyces.biomeextensions.mixin.api;

import dev.dhyces.biomeextensions.ApiAccess;
import dev.dhyces.biomeextensions.impl.ApiAccessImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ApiAccess.class)
public interface ApiAccessMixin {

    /**
     * @author dhyces
     * @reason impl api
     */
    @Overwrite(remap = false)
    static ApiAccess getInstance() {
        return ApiAccessImpl.INSTANCE;
    }

}
