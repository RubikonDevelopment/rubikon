package dev.inditium.modules;

import dev.inditium.Inditium;
import dev.inditium.api.modules.Module;
import dev.inditium.api.modules.Toggleable;

public class Test extends Module implements Toggleable {
    @Override
    public void onInitialize() {
        Inditium.LOGGER.info("Test module initialized");
    }

    @Override
    public void onEnable() {
        Inditium.LOGGER.info("Test module enabled");
    }

    @Override
    public void onDisable() {
        Inditium.LOGGER.info("Test module disabled");
    }
}
