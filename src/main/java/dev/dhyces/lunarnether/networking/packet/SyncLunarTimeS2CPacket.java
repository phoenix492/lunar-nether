package dev.dhyces.lunarnether.networking.packet;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import dev.dhyces.lunarnether.LunarNether;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

public record SyncLunarTimeS2CPacket(long time) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SyncLunarTimeS2CPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "sync_lunar_time_s2c"));
    public static final StreamCodec<ByteBuf, SyncLunarTimeS2CPacket> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.VAR_LONG,
        SyncLunarTimeS2CPacket::time,
        SyncLunarTimeS2CPacket::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
