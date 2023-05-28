package dev.rubikon.events;

import dev.rubikon.utils.Event;
import net.minecraft.network.packet.Packet;

/**
 * Represents the packet outgoing event.
 * <p>
 *     This event is fired when the client sends a packet to the server.
 * </p>
 *
 * @see Event
 */
public class PacketOutgoingEvent implements Event {
    public PacketOutgoingEvent(Packet<?> packet) {

    }
}
