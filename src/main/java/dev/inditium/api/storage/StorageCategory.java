package dev.inditium.api.storage;

import dev.inditium.Inditium;

import java.io.File;

public interface StorageCategory {
    String getStoragePath();

    default void save(String name, String data) {
        Inditium.STORAGE.save(getStoragePath() + "/" + name, data);
    }

    default String get(String name) {
        return Inditium.STORAGE.get(getStoragePath() + "/" + name);
    }

    default File[] getFiles() {
        return Inditium.STORAGE.getFiles(getStoragePath());
    }
}
