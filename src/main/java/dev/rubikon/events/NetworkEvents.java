package dev.rubikon.events;

import dev.rubikon.utils.Event;
import net.minecraft.network.packet.s2c.login.LoginHelloS2CPacket;
import net.minecraft.network.packet.s2c.login.LoginSuccessS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;

/**
 * Represents the network events.
 * <p>
 *     This class contains all network-related events.
 * </p>
 *
 * @see Event
 */
public class NetworkEvents implements Event {
    public static class HelloEvent extends NetworkEvents {
        public HelloEvent(LoginHelloS2CPacket packet) {

        }
    }
    public static class ServerTryJoin extends NetworkEvents {
        public ServerTryJoin(String serverId) {

        }
    }
    public static class JoinEvent extends NetworkEvents {
        public JoinEvent(LoginSuccessS2CPacket packet) {

        }
    }
    public static class JoinGameEvent extends NetworkEvents {
        public JoinGameEvent(GameJoinS2CPacket packet) {

        }
    }
}
