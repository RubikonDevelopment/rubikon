package dev.rubikon.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

@UtilityClass
public class NbtUtils {
    public static <T extends Serializable<?>> NbtList listToTag(Iterable<T> list) {
        NbtList tag = new NbtList();
        for (T item : list)
            tag.add(item.toNbtTag());

        return tag;
    }

    public static <T extends Serializable<?>> NbtList stringListToTag(Iterable<String> list) {
        NbtList tag = new NbtList();
        for (String item : list)
            tag.add(NbtString.of(item));

        return tag;
    }
}
