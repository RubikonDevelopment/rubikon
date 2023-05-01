package dev.rubikon.mixin;

import dev.rubikon.Rubikon;
import dev.rubikon.events.TickEvent;
import dev.rubikon.renderer.core.imgui.ImGuiContext;
import dev.rubikon.renderer.core.nanovg.NVContext;
import dev.rubikon.stores.Stores;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Inject(at = @At("TAIL"), method = "<init>")
	private void init(CallbackInfo info) {
		//only for initializing rendering only things
		NVContext.initialize();
		ImGuiContext.initialize();
		Stores.initRenderer();
	}
	@Inject(at = @At("TAIL"), method = "tick")
	private void tick(CallbackInfo info) {
		Rubikon.getEventPubSub().publish(new TickEvent());
	}

}
