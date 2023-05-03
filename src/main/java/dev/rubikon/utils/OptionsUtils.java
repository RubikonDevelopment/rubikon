package dev.rubikon.utils;

import dev.rubikon.settings.ListOption;
import dev.rubikon.settings.Option;
import dev.rubikon.settings.types.StringListOption;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

/**
 * Utility class for options.
 *
 * @see Option
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

    public static String listToString(Option<?> option) {
        return ((ListOption<?>) option).get().stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
