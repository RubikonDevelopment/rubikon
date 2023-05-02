package dev.rubikon.settings;

import java.util.List;

/**
 * Interface for mark all options that are lists.
 * @param <T> Type of the values in the list.
 *
 * @see Option
 */
public interface ListOption<T> {
    List<T> get();
}
