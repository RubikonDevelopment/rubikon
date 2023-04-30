package dev.rubikon;

import dev.rubikon.api.commons.Event;
import dev.rubikon.listeners.FeatureListener;
import dev.rubikon.listeners.GuiListener;
import dev.rubikon.stores.Stores;
import io.github.nevalackin.radbus.PubSub;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rubikon implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Rubikon");
	@Getter
	private static final PubSub<Event> eventPubSub = PubSub.newInstance(LOGGER::error);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Procgit eed with mild caution.

		Stores.init();

		eventPubSub.subscribe(new GuiListener());
		eventPubSub.subscribe(new FeatureListener());
	}
}
