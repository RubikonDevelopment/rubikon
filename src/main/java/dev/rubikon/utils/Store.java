package dev.rubikon.utils;

import dev.rubikon.settings.Option;

import java.util.Collection;

public interface Store<T, U> {
    default void add(U item) {};
    default void add(T name, U item) {};
    U find(T name);
    Collection<T> names();
    Collection<? extends U> all();
}
