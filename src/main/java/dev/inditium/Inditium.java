package dev.inditium;

import dev.inditium.managers.ModuleManager;
import dev.inditium.scripts.ScriptLoader;
import dev.inditium.storage.Storage;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Inditium implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Inditium");
	public static final Storage STORAGE = new Storage();
	public static final ModuleManager MODULE_MANAGER = new ModuleManager();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		MODULE_MANAGER.init();
		new ScriptLoader().load();
	}
}
