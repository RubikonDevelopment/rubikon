package dev.rubikon;

import dev.rubikon.utils.Event;
import dev.rubikon.renderer.core.Renderer;
import dev.rubikon.things.Things;
import io.github.nevalackin.radbus.PubSub;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * The main class of the Rubikon mod.
 */
public class Rubikon implements ModInitializer {
	private static final String MOD_ID = "rubikon";

	/**
	 * Base logger for Rubikon.
	 * @see <a href="https://www.slf4j.org/">SLF4J</a>
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	/**
	 * The folder where all the Rubikon files are stored.
	 */
	public static final File FOLDER = FabricLoader.getInstance().getGameDir().resolve(MOD_ID).toFile();

	@Getter
	private static final PubSub<Event> eventPubSub = PubSub.newInstance(LOGGER::error);

	/**
	 * Code that runs on Minecraft initialization.
	 * @see dev.rubikon.mixin.MinecraftClientMixin
	 * @see ModInitializer#onInitialize()
	 */
	@Override
	public void onInitialize() {
		if (!FOLDER.exists()) {
			FOLDER.getParentFile().mkdirs();
			FOLDER.mkdir();
		}

		new Renderer(); // Prepare the renderer

		Things.init();
		Things.load();
	}
}
