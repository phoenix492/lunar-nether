package dev.dhyces.lunarnether.networking.handler;

import net.neoforged.neoforge.network.handling.IPayloadContext;

import dev.dhyces.lunarnether.LunarNetherClient;
import dev.dhyces.lunarnether.networking.packet.SyncLunarTimeS2CPacket;

public abstract class SyncLunarTimeHandler {
    public static void handleClient(SyncLunarTimeS2CPacket packet, IPayloadContext context) {
        LunarNetherClient.netherDayTime = packet.time();
    }
}
