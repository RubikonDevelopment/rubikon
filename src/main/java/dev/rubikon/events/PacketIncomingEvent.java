package dev.rubikon.events;

import dev.rubikon.utils.Event;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;

public class PacketIncomingEvent implements Event {
    public PacketIncomingEvent(Packet<?> packet, PacketListener listener) {}
}
