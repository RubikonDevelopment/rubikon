package dev.rubikon.utils;

import dev.rubikon.settings.ListOption;
import dev.rubikon.settings.Option;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

/**
 * Represents a utility class for option-related operations.
 */
@UtilityClass
public class OptionsUtils {
    /**
     * Checks if an option is a list.
     * <p>
     *     List options are options that can have multiple values.
     *     They are marked with the {@link ListOption} interface.
     * </p>
     * @param option The option to check.
     * @return `true` if the option is a list, `false` otherwise.
     */
    public static boolean isList(Option<?> option) {
        return option instanceof ListOption;
    }

    /**
     * Converts a list option to a string.
     * @param option The option to convert.
     * @return The string representation of the option.
     *
     * @throws ClassCastException If the option is not a list. Use {@link #isList(Option)} to check.
     */
    public static String listToString(Option<?> option) {
        return ((ListOption<?>) option).get().stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
