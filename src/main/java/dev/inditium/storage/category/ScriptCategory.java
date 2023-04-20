package dev.inditium.storage.category;

import dev.inditium.api.storage.StorageCategory;

public class ScriptCategory implements StorageCategory {
    @Override
    public String getStoragePath() {
        return "scripts";
    }
}
