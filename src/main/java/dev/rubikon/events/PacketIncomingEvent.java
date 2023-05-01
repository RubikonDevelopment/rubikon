package dev.rubikon.events;

import dev.rubikon.api.commons.Event;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;

public class PacketIncomingEvent implements Event {
    public PacketIncomingEvent(Packet<?> packet, PacketListener listener) {}
}
