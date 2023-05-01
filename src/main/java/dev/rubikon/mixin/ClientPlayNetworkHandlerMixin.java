package dev.rubikon.mixin;

import dev.rubikon.things.Things;
import dev.rubikon.things.commands.Commands;
import dev.rubikon.utils.ChatUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo ci) {
        if (!message.startsWith(".")) return;

        try {
            Commands.get().dispatch(message.substring(1));
        } catch (Exception e) {
            ChatUtils.sendMessage(Text.of("Commands"), e.getMessage());
        }

        client.inGameHud.getChatHud().addToMessageHistory(message);
        ci.cancel();
    }
}
