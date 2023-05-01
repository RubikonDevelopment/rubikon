package dev.rubikon.mixin;

import dev.rubikon.Rubikon;
import dev.rubikon.events.NetworkEvents;
import net.minecraft.network.packet.s2c.login.LoginHelloS2CPacket;
import net.minecraft.network.packet.s2c.login.LoginSuccessS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.client.network.ClientLoginNetworkHandler.class)
public class ClientLoginNetworkHandlerMixin {
    @Inject(method = "onHello", at = @At("HEAD"))
    public void onHello(LoginHelloS2CPacket packet, CallbackInfo ci) {
        Rubikon.getEventPubSub().publish(new NetworkEvents.HelloEvent(packet));
    }
    @Inject(method = "joinServerSession", at = @At("HEAD"))
    public void onTryJoinServer(String serverId, CallbackInfoReturnable<Text> cir) {
        Rubikon.getEventPubSub().publish(new NetworkEvents.ServerTryJoin(serverId));
    }
    @Inject(method = "onSuccess", at = @At("HEAD"))
    public void onSuccessfulJoin(LoginSuccessS2CPacket packet, CallbackInfo ci) {
        Rubikon.getEventPubSub().publish(new NetworkEvents.JoinEvent(packet));
    }
}
