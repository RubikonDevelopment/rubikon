package dev.rubikon.settings;

import dev.rubikon.utils.Serializable;
import lombok.Getter;
import net.minecraft.nbt.NbtCompound;

/**
 * Represents an options that can be changed by the user.
 * @param <T> The type of the option's value.
 */
public abstract class Option<T> implements Serializable<T> {
    @Getter
    private final String name, description;

    @Getter
    private final T defaultValue;
    private T value;

    public Option(String name, String description, T defaultValue) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
    }

    public T get() {
        return value != null ? value : defaultValue;
    }

    public boolean set(T value) {
        if (!isValid(value)) return false;

        this.value = value;
        return true;
    }

    protected abstract NbtCompound save(NbtCompound nbt);
    protected abstract T load(NbtCompound nbt);
    public abstract boolean isValid(T value);

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
