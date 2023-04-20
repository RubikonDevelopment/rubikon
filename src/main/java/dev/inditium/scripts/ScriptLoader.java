package dev.inditium.scripts;

import dev.inditium.Inditium;
import dev.inditium.api.loaders.Loader;

import java.io.File;

public class ScriptLoader implements Loader {
    @Override
    public void load() {
        for (File file : Inditium.STORAGE.SCRIPTS.getFiles()) {
            Inditium.LOGGER.info(Inditium.STORAGE.SCRIPTS.get(file.getName()));
        };
    }
}
