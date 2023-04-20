package dev.inditium.api.storage;

import dev.inditium.Inditium;

import java.io.File;
import java.nio.file.Files;

public class Storage {
    private File store;

    public Storage() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            store = new File(System.getenv("APPDATA") + "/inditium");
        } else {
            store = new File(System.getProperty("user.home") + "/.inditium");
        }

        if (!store.exists()) store.mkdirs();
    }

    public void save(String name, String data) {
        File file = new File(store, name);

        try {
            if (!file.exists()) file.createNewFile();

            Files.writeString(file.toPath(), data);
        } catch (Exception e) {
            Inditium.LOGGER.error("Failed to create file: " + name, e);
        }
    }

    public String get(String name) {
        File file = new File(store, name);

        try {
            if (!file.exists()) return null;

            return Files.readString(file.toPath());
        } catch (Exception e) {
            Inditium.LOGGER.error("Failed to read file: " + name, e);
        }

        return null;
    }

    public File[] getFiles() {
        return store.listFiles();
    }

    public File[] getFiles(String name) {
        File file = new File(store, name);

        if (!file.exists()) {
            file.mkdirs();
            return new File[0];
        };
        return file.listFiles();
    }

    public void initCategory(StorageCategory category) {
        File file = new File(store, category.getStoragePath());

        if (!file.exists()) file.mkdirs();
    }
}
