package dev.dhyces.lunarnether.server.saveddata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;

import dev.dhyces.lunarnether.networking.packet.SyncLunarTimeS2CPacket;
import org.jetbrains.annotations.NotNull;

public class LunarTimeData extends SavedData {
    @Nullable
    public static ServerLevel currentNether;

    private long daytime = 0;

    private LunarTimeData() {}

    public static LunarTimeData create() {
        return new LunarTimeData();
    }

    public static LunarTimeData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        return LunarTimeData.create();
    }

    public static LunarTimeData getOrCreate(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(new Factory<>(LunarTimeData::create, LunarTimeData::load), "lunarnether-daytime");
    }

    public long daytime() {
        return daytime;
    }

    public void update(long overworldDaytime) {
        if (daytime != overworldDaytime) {
            daytime = overworldDaytime;
            PacketDistributor.sendToPlayersInDimension(currentNether, new SyncLunarTimeS2CPacket(daytime));
            setDirty();
        }
    }

    public static float netherTimeOfDay(long daytime) {
        double decimal = Mth.frac(daytime / (24000.0 * 8) - 0.25);
        double d1 = 0.5 - Math.cos(decimal * Math.PI) / 2;
        return (float)(decimal * 2 + d1) / 3.0F;
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag pCompoundTag, HolderLookup.@NotNull Provider provider) {
        pCompoundTag.putLong("daytime", daytime);
        return pCompoundTag;
    }
}
