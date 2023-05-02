package dev.rubikon.utils;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... value) {
        return new ArrayList<T>(List.of(value));
    }

    public static <T> ImmutableList<T> toImmutableList(List<T> value) {
        return ImmutableList.<T>builder().addAll(value).build();
    }
}
