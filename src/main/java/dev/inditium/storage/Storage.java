package dev.inditium.storage;

import dev.inditium.storage.category.ModuleCategory;
import dev.inditium.storage.category.ScriptCategory;

public class Storage extends dev.inditium.api.storage.Storage {
    public ScriptCategory SCRIPTS = new ScriptCategory();
    public ModuleCategory MODULES = new ModuleCategory();

    public Storage() {
        super();

        initCategory(SCRIPTS);
        initCategory(MODULES);
    }
}
