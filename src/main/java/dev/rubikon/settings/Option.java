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
 * @param <T> The type of the option's value.
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

    public T get() {
        return value;
    }

    public boolean set(T value) {
        if (!isValid(value)) return false;

        this.value = value;
        return true;
    }

    protected abstract NbtCompound save(NbtCompound nbt);
    protected abstract T load(NbtCompound nbt);
    public abstract boolean isValid(T value);

    public abstract boolean parse(String value);
    protected boolean parse(Object value) {
        return value != null && set((T) value);
    }

    public List<String> commandSuggestions() {
        return COMMAND_SUGGESTIONS;
    }

    @Nullable
    public Iterable<Identifier> commandIdentifierSuggestions() {
        return null;
    }

    @Override
    public NbtCompound toNbtTag() {
        NbtCompound nbt = new NbtCompound();

        nbt.putString("name", name);

        return save(nbt);
    }

    @Override
    public T fromNbtTag(NbtCompound nbt) {
        return load(nbt);
    }
}
