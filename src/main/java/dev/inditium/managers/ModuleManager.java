package dev.inditium.managers;

import dev.inditium.api.modules.Module;
import dev.inditium.api.stores.Store;
import dev.inditium.modules.Test;

public class ModuleManager extends Store<String, Module> {
    public void init() {
        add("test", new Test());

        for (Module module : values()) module.onInitialize();
    }
}
