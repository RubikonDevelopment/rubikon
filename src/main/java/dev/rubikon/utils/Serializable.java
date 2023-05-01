package dev.rubikon.utils;


import net.minecraft.nbt.NbtCompound;

/**
 * This interface is used to serialize and deserialize objects.
 * @param <T> The type of the object to be serialized.
 */
public interface Serializable<T> {
    NbtCompound toNbtTag();
    T fromNbtTag(NbtCompound nbt);
}
