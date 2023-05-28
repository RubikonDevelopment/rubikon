package dev.rubikon.things;

import dev.rubikon.Rubikon;
import dev.rubikon.utils.Serializable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;

import java.io.File;
import java.io.IOException;

/**
 * Represents a base class for all things.
 * <p>
 *     This class provides a few helper methods and fields to make the process easier.
 *     <br>
 *     <br>
 *     To create a new thing, you need to extend this class and implement the {@link #toNbtTag()} and {@link #fromNbtTag(NbtCompound)} methods.
 *     <br>
 *     <br>
 *     To register a thing, you need to call the {@link Things#add(Thing)} method.
 * </p>
 * @param <T> The type of the thing to store.
 *           <p>
 *              This is used to provide a type-safe way to serialize and deserialize the thing.
 *           </p>
 *
 * @see Things
 * @see Serializable
 */
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

    /**
     * Initialize the thing.
     * <p>
     *     This method is called in the {@link Things#add(Thing)} method.
     * </p>
     *
     * @see Things#add(Thing)
     * @see Things#init()
     */
    public abstract void init();

    /**
     * Save the thing to a file.
     * <p>
     *     This method uses the {@link #file} field to determine the file to save to.
     *     <br>
     *     It also uses the {@link #toNbtTag()} method to serialize the thing.
     *     <br>
     *     <br>
     *     If the file does not exist, it will be created.
     *     <br>
     *     If the file already exists, it will be overwritten.
     *     <br>
     *     <br>
     *     If you want to save the thing to a different profile (sub-folder), use the {@link #save(File)} method instead.
     * </p>
     *
     * @see #save(File)
     */
    public void save() {
        save(null);
    }

    /**
     * Save the thing to a file.
     * <p>
     *     This method uses the {@link #file} field to determine the file to save to.
     *     <br>
     *     It also uses the {@link #toNbtTag()} method to serialize the thing.
     *     <br>
     *     <br>
     *     If the file does not exist, it will be created.
     *     <br>
     *     If the file already exists, it will be overwritten.
     * </p>
     *
     * @param folder The folder to save the file to.
     */
    public void save(File folder) {
        File file = folder == null ? this.file : new File(folder.getPath(), this.file.getName());

        try {
            NbtCompound nbt = toNbtTag();
            if (nbt == null) return;

            NbtIo.write(nbt, file);
        } catch (IOException e) {
            Rubikon.LOGGER.error("Failed to save file: " + file.getAbsolutePath());
        }
    }

    /**
     * Load the thing from a file.
     * <p>
     *     This method uses the {@link #file} field to determine the file to load from.
     *     <br>
     *     It also uses the {@link #fromNbtTag(NbtCompound)} method to deserialize the thing.
     *     <br>
     *     <br>
     *     If the file does not exist, it will be created.
     *     <br>
     *     If the file already exists, it will be overwritten.
     *     <br>
     *     <br>
     *     If you want to lood the thing from a different profile (sub-folder), use the {@link #load(File)} method instead.
     * </p>
     *
     * @see #load(File)
     */
    public void load() {
        load(null);
    }

    /**
     * Load the thing from a file.
     * <p>
     *     This method uses the {@link #file} field to determine the file to load from.
     *     <br>
     *     It also uses the {@link #fromNbtTag(NbtCompound)} method to deserialize the thing.
     *     <br>
     *     <br>
     *     If the file does not exist, it will be created.
     *     <br>
     *     If the file already exists, it will be overwritten.
     * </p>
     *
     * @param folder The folder to load the file from.
     */
    public void load(File folder) {
        File file = folder == null ? this.file : new File(folder.getPath(), this.file.getName());

        try {
            fromNbtTag(NbtIo.read(file));
        } catch (IOException e) {
            Rubikon.LOGGER.error("Failed to load file: " + file.getAbsolutePath());
        }
    }
}
