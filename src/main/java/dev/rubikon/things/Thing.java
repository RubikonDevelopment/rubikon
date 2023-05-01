package dev.rubikon.things;

import dev.rubikon.Rubikon;
import dev.rubikon.utils.Serializable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;

import java.io.File;
import java.io.IOException;

public abstract class Thing<T> implements Serializable<T> {
    private final String name;
    private final File file;

    public Thing(String name) {
        this.name = name;
        this.file = new File(Rubikon.FOLDER, name + ".nbt");

        if (!file.exists()) {
            try {
                NbtIo.write(new NbtCompound(), file); // Create empty file
            } catch (IOException e) {
                Rubikon.LOGGER.error("Failed to create file: " + file.getAbsolutePath());
            }
        }
    }

    public void init() {}

    public void save() {
        try {
            NbtCompound nbt = toNbtTag();
            if (nbt == null) return;

            NbtIo.write(nbt, file);
        } catch (IOException e) {
            Rubikon.LOGGER.error("Failed to save file: " + file.getAbsolutePath());
        }
    }

    public void load() {
        try {
            fromNbtTag(NbtIo.read(file));
        } catch (IOException e) {
            Rubikon.LOGGER.error("Failed to load file: " + file.getAbsolutePath());
        }
    }
}
