package dev.rubikon.utils;


import net.minecraft.nbt.NbtCompound;

/**
 * This interface is used to save and load data from the config(json) file.
 */
public interface Serializable<T> {
    NbtCompound toNbtTag();
    T fromNbtTag(NbtCompound nbt);
}
