package dev.rubikon.utils;

import java.util.Collection;

/**
 * This interface is used to mark all stores.
 * @param <T> The type of the store's keys.
 * @param <U> The type of the store's values.
 */
public interface Store<T, U> {
    /**
     * Adds an item to the store.
     * @param item The item to add.
     */
    default void add(U item) {};

    /**
     * Adds an item to the store.
     * @param name The name of the item.
     * @param item The item to add.
     */
    default void add(T name, U item) {};

    /**
     * Finds an item in the store by its name.
     * @param name The name of the item.
     * @return The item if found, `null` otherwise.
     */
    U find(T name);

    /**
     * @return A collection of all names in the store.
     */
    Collection<T> names();

    /**
     * @return A collection of all items in the store.
     */
    Collection<? extends U> all();
}
