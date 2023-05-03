package dev.rubikon.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

/**
 * Represents a utility class for NBT-related operations.
 */
@UtilityClass
public class NbtUtils {
    /**
     * Converts a list of serializable objects to an NbtList.
     * @param list The list of serializable objects to convert.
     * @return The converted NbtList.
     *
     * @see Serializable
     * @see NbtList
     */
    public static <T extends Serializable<?>> NbtList listToTag(Iterable<T> list) {
        NbtList tag = new NbtList();
        for (T item : list)
            tag.add(item.toNbtTag());

        return tag;
    }

    /**
     * Converts a list of strings to an NbtList.
     * @param list The list of strings to convert.
     * @return The converted NbtList.
     *
     * @see Serializable
     * @see NbtList
     * @see NbtString
     */
    public static <T extends Serializable<?>> NbtList stringListToTag(Iterable<String> list) {
        NbtList tag = new NbtList();
        for (String item : list)
            tag.add(NbtString.of(item));

        return tag;
    }
}
