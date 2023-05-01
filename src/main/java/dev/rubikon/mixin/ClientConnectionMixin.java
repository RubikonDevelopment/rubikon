package dev.rubikon.mixin;

import dev.rubikon.Rubikon;
import dev.rubikon.api.commons.Event;
import dev.rubikon.events.PacketIncomingEvent;
import dev.rubikon.events.PacketOutgoingEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("HEAD"))
    private static <T extends PacketListener> void handlePacketIncoming(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        Rubikon.getEventPubSub().publish(new PacketIncomingEvent(packet, listener));
    }
    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V",
            at = @At("HEAD"))
    public void handlePacketOutgoing(Packet<?> packet, CallbackInfo ci) {
        Rubikon.getEventPubSub().publish(new PacketOutgoingEvent(packet));
    }
}
