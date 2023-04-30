package dev.rubikon;

import dev.rubikon.api.renderer.core.imgui.ImGuiContext;
import dev.rubikon.events.ScreenRenderCallback;
import dev.rubikon.repositories.FeatureRepository;
import imgui.ImGui;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Rubikon implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Rubikon");
	private final FeatureRepository featureRepository = new FeatureRepository();
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ScreenRenderCallback.EVENT.register((tickDelta -> {
			ImGuiContext.draw((call)->{
				ImGui.begin("test");
				ImGui.text("imgui is working yeey");
				ImGui.end();
			});
		}));
	}

}
