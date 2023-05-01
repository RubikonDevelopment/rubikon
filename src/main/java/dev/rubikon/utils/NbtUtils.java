package dev.rubikon.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class NbtUtils {
    public static <T extends Serializable<?>> NbtList listToTag(Iterable<T> list) {
        NbtList tag = new NbtList();
        for (T item : list)
            tag.add(item.toNbtTag());

        return tag;
    }
}
