package dev.rubikon.settings;

import com.google.common.collect.ImmutableList;
import dev.rubikon.utils.Serializable;
import lombok.Getter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represents an options that can be changed by the user.
 * <p>
 *     Options are used to store user preferences.
 *     They can be changed by the user and are saved to the nbt.
 *     <br>
 *     <br>
 *     Options are in groups, which are represented by {@link OptionGroup}.
 *     <br>
 *     <br>
 *     To create a new type of option, you need to extend this class.
 * </p>
 * @param <T> The type of the option's value.
 *
 * @see ListOption
 * @see OptionGroup
 * @see Options
 */
public abstract class Option<T> implements Serializable<T> {
    private static final List<String> COMMAND_SUGGESTIONS = ImmutableList.of();

    @Getter
    private final String name, description;

    @Getter
    private final T defaultValue;
    @Getter
    private T value;

    public Option(String name, String description, T defaultValue) {
        this(name, description, defaultValue, defaultValue);
    }

    public Option(String name, String description, T value, T defaultValue) {
        this.name = name;
        this.description = description;
        this.value = value; // is replaced later with load()
        this.defaultValue = defaultValue;
    }

    /**
     * @return the value of the option.
     */
    public T get() {
        return value;
    }

    /**
     * Sets the value of the option.
     * @param value The new value.
     * @return `true` if the value was set successfully, `false` otherwise.
     */
    public boolean set(T value) {
        if (!isValid(value)) return false;

        this.value = value;
        return true;
    }

    /**
     * Saves the option value to the nbt.
     * @param nbt The nbt to save to.
     * @return The nbt with the option value saved.
     *
     * @see #toNbtTag()
     */
    protected abstract NbtCompound save(NbtCompound nbt);
    /**
     * Loads the option value from the nbt.
     * @param nbt The nbt to load from.
     * @return The option value.
     *
     * @see #fromNbtTag(NbtCompound)
     */
    protected abstract T load(NbtCompound nbt);

    /**
     * Checks if the value is valid.
     * @param value The value to check.
     * @return `true` if the value is valid, `false` otherwise.
     */
    public abstract boolean isValid(T value);

    /**
     * Parses the value from a string.
     * <p>
     *     This method is extended by different types of options.
     *     <br>
     *     <br>
     *     It calls {@link #parse(Object)} with the parsed value at bottom.
     * </p>
     * @param value The string to parse.
     * @return `true` if the value was parsed successfully, `false` otherwise.
     *
     * @see #parse(Object)
     */
    public abstract boolean parse(String value);

    /**
     * Parses the value from an object.
     * @param value The object to parse.
     * @return `true` if the value was parsed successfully, `false` otherwise.
     */
    protected boolean parse(Object value) {
        return value != null && set((T) value);
    }

    /**
     * @return The suggestions for the command.
     */
    public List<String> commandSuggestions() {
        return COMMAND_SUGGESTIONS;
    }

    /**
     * <p>
     *     This can be null if the method is not overridden.
     * </p>
     * @return The suggestions for the command identifier.
     * @see Identifier
     */
    @Nullable
    public Iterable<Identifier> commandIdentifierSuggestions() {
        return null;
    }

    /**
     * Converts the option to a nbt tag.
     * <p>
     *     This method calls {@link #save(NbtCompound)} at bottom.
     * </p>
     * @return The nbt tag.
     *
     * @see #save(NbtCompound)
     * @see #fromNbtTag(NbtCompound)
     */
    @Override
    public NbtCompound toNbtTag() {
        NbtCompound nbt = new NbtCompound();

        nbt.putString("name", name);

        return save(nbt);
    }

    /**
     * Converts the nbt tag to an option.
     * @param nbt The nbt tag.
     * @return The option.
     */
    @Override
    public T fromNbtTag(NbtCompound nbt) {
        return load(nbt);
    }
}
