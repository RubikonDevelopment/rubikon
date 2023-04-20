package dev.inditium.storage.category;

import dev.inditium.api.storage.StorageCategory;

public class ModuleCategory implements StorageCategory {
    @Override
    public String getStoragePath() {
        return "modules";
    }
}
