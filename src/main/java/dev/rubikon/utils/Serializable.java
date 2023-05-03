package dev.rubikon.utils;


import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

/**
 * This interface is used to serialize and deserialize objects.
 * @param <T> The type of the object to be serialized.
 */
public interface Serializable<T> {
    @Nullable NbtCompound toNbtTag();
    T fromNbtTag(NbtCompound nbt);
}
