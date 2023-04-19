package dev.inditium;

import dev.inditium.managers.ModuleManager;
import dev.inditium.renderer.nanvog.core.NVContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.lwjgl.nanovg.NVGColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lwjgl.nanovg.NanoVG.*;

public class Inditium implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Inditium");
	public static final ModuleManager MODULE_MANAGER = new ModuleManager();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		MODULE_MANAGER.init();

		HudRenderCallback.EVENT.register((e,l) -> {
			NVContext.draw((ctx) ->{
				nvgBeginPath(ctx);
				nvgRect(ctx, 10, 10, 100, 100);
				nvgFillColor(ctx, nvgColor(-1));
				nvgFill(ctx);
				nvgClosePath(ctx);
			});
		});
	}

	public static NVGColor nvgColor(int argb) {
		NVGColor _res = NVGColor.create();
		nvgRGBA((byte) (argb >> 16 & 255), (byte) (argb >> 8 & 255), (byte) (argb >> 0 & 255), (byte) (argb >> 24 & 255), _res);
		return _res;
	}

	public static NVGColor rgba(int r, int g, int b, int a, NVGColor color) {
		color.r(r / 255.0F);
		color.g(g / 255.0F);
		color.b(b / 255.0F);
		color.a(a / 255.0F);
		return color;
	}
}
