package dev.rubikon.mixin;

import dev.rubikon.Rubikon;
import dev.rubikon.events.DisconnectEvent;
import dev.rubikon.events.TickEvent;
import dev.rubikon.renderer.core.Renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Shadow @Nullable public ClientWorld world;

	@Inject(at = @At("TAIL"), method = "<init>")
	private void init(CallbackInfo info) {
		// We need to initialize the renderer here because the game crashes if we do it in Rubikon#onInitialize()
		Renderer.getInstance().init();
	}
	@Inject(at = @At("TAIL"), method = "tick")
	private void tick(CallbackInfo info) {
		Rubikon.getEventPubSub().publish(new TickEvent());
	}

	@Inject(at = @At("HEAD"), method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;)V")
	private void onDisconnect(Screen screen, CallbackInfo info) {
		if (world != null) {
			Rubikon.getEventPubSub().publish(new DisconnectEvent());
		}
	}
}
