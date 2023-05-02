package dev.rubikon.utils;

import dev.rubikon.settings.ListOption;
import dev.rubikon.settings.Option;
import dev.rubikon.settings.types.StringListOption;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class OptionsUtils {
    public static boolean isList(Option<?> option) {
        return option instanceof StringListOption;
    }

    public static String listToString(Option<?> option) {
        return ((ListOption<?>) option).get().stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
