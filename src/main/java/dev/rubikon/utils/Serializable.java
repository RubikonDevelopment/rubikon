package dev.rubikon.utils;


import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

/**
 * This interface is used to mark all objects that can be serialized to nbt.
 * @param <T> The type of the object to be serialized.
 */
public interface Serializable<T> {
    /**
     * Serializes the object to nbt.
     * @return The nbt tag.
     */
    @Nullable NbtCompound toNbtTag();

    /**
     * Deserializes the object from nbt.
     * @param nbt The nbt tag.
     * @return The deserialized object.
     */
    T fromNbtTag(NbtCompound nbt);
}
