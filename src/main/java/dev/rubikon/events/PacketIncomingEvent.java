package dev.rubikon.events;

import dev.rubikon.utils.Event;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;

/**
 * Represents the packet incoming event.
 * <p>
 *     This event is fired when the client receives a packet from the server.
 * </p>
 *
 * @see Event
 */
public class PacketIncomingEvent implements Event {
    public PacketIncomingEvent(Packet<?> packet, PacketListener listener) {}
}
