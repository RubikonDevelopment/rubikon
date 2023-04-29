package dev.inditium;

import dev.inditium.api.renderer.core.nanovg.NVContext;
import dev.inditium.repositories.FeatureRepository;
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
	private final FeatureRepository featureRepository = new FeatureRepository();
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		HudRenderCallback.EVENT.register((e, l) -> {
			NVContext.draw((vg) ->{
				nvgRect(vg, 10, 10, 100, 100);
				nvgFillColor(vg, nvgColor(-1));
				nvgFill(vg);
			});
		});
	}
	public static NVGColor nvgColor(int argb) {
		NVGColor _res = NVGColor.create();
		nvgRGBA((byte) (argb >> 16 & 255), (byte) (argb >> 8 & 255), (byte) (argb >> 0 & 255), (byte) (argb >> 24 & 255), _res);
		return _res;
	}

}
