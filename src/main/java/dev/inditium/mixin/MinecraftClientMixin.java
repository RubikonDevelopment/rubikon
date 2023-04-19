package dev.inditium.mixin;

import dev.inditium.renderer.nanvog.core.NVContext;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Inject(at = @At("TAIL"), method = "<init>")
	private void init(CallbackInfo info) {
		//only for initializing nanoVG
		NVContext.initialize();
	}
}
