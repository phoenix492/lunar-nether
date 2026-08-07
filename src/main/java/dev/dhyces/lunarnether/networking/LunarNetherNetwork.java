package dev.dhyces.lunarnether.networking;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.networking.handler.SyncLunarTimeHandler;
import dev.dhyces.lunarnether.networking.packet.SyncLunarTimeS2CPacket;

@EventBusSubscriber
public class LunarNetherNetwork {
    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(LunarNether.MODID)
            .versioned("1")
            .optional();
        registrar.playToClient(
            SyncLunarTimeS2CPacket.TYPE,
            SyncLunarTimeS2CPacket.STREAM_CODEC,
            SyncLunarTimeHandler::handleClient
        );
    }
}
