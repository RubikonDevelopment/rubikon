package dev.rubikon;

import dev.rubikon.api.commons.Event;
import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.listeners.FeatureListener;
import dev.rubikon.renderer.core.imgui.ImGuiContext;
import dev.rubikon.stores.Stores;
import imgui.ImGui;
import io.github.nevalackin.radbus.PubSub;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main class of the Rubikon mod.
 */
public class Rubikon implements ModInitializer {
	/**
	 * Base logger for Rubikon.
	 * @see <a href="https://www.slf4j.org/">SLF4J</a>
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger("Rubikon");
	@Getter
	private static final PubSub<Event> eventPubSub = PubSub.newInstance(LOGGER::error);

	/**
	 * Code that runs on Minecraft initialization.
	 * It is not recommended to add anything here, use {@link FeatureListener} instead.
	 * @see ModInitializer#onInitialize()
	 */
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Procgit eed with mild caution.
		eventPubSub.subscribe(new FeatureListener());
	}
}
