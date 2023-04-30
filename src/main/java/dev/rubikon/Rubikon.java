package dev.rubikon;

import dev.rubikon.api.commons.Event;
import dev.rubikon.api.renderer.core.imgui.ImGuiContext;
import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.repositories.FeatureRepository;
import imgui.ImGui;
import io.github.nevalackin.radbus.PubSub;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Rubikon implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Rubikon");
	private static final FeatureRepository featureRepository = new FeatureRepository();
	private static final PubSub<Event> eventPubSub = PubSub.newInstance(LOGGER::error);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Procgit eed with mild caution.
		featureRepository.init();
		eventPubSub.subscribe(ScreenRenderEvent.class,screenRenderEvent -> {
			ImGuiContext.draw(draw ->{
				ImGui.begin("test");
				ImGui.text("yes");
				ImGui.end();
			});
		});
	}

	public static PubSub<Event> getEventPubSub() {
		return eventPubSub;
	}

	public static FeatureRepository getFeatureRepository() {
		return featureRepository;
	}
}
