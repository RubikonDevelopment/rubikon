package dev.rubikon.settings;

import java.util.List;

/**
 * Interface for mark all options that are lists.
 * @param <T> Type of the values in the list.
 * @see dev.rubikon.settings.types.StringListOption
 */
public interface ListOption<T> {
    List<T> get();
}
