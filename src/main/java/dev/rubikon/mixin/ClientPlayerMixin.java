package dev.rubikon.mixin;

import dev.rubikon.Rubikon;
import dev.rubikon.events.UpdateEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin {

    @Inject(at = @At("TAIL"), method = "sendMovementPackets")
    public void onSendPosition(CallbackInfo info) {
        Rubikon.getEventPubSub().publish(new UpdateEvent());
    }
}
