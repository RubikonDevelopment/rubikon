package dev.rubikon.events;

import dev.rubikon.api.commons.Event;
import net.minecraft.network.packet.Packet;

public class PacketOutgoingEvent implements Event {
    public PacketOutgoingEvent(Packet<?> packet) {

    }
}
