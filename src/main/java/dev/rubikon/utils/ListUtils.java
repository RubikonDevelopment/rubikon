package dev.rubikon.utils;

import com.google.common.collect.ImmutableList;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a utility class for list-related operations.
 */
@UtilityClass
public class ListUtils {
    /**
     * Converts a list of values to an ArrayList.
     * @param value The list of values to convert.
     * @return The converted ArrayList.
     *
     * @see ArrayList
     */
    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... value) {
        return new ArrayList<T>(List.of(value));
    }

    /**
     * Converts a list of values to an ImmutableList.
     * @param value The list of values to convert.
     * @return The converted ImmutableList.
     *
     * @see ImmutableList
     */
    public static <T> ImmutableList<T> toImmutableList(List<T> value) {
        return ImmutableList.<T>builder().addAll(value).build();
    }
}
