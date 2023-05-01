package dev.rubikon.things;

import dev.rubikon.things.commands.Commands;
import dev.rubikon.things.features.Features;

import java.util.HashMap;
import java.util.Map;

public class Things {
    private static final Map<Class<? extends Thing>, Thing<?>> things = new HashMap<>();

    public static void init() {
        add(new Features());
        add(new Commands());
    }

    public static void load() {
        things.values().forEach(Thing::load);
    }

    private static void add(Thing<?> thing) {
        things.put(thing.getClass(), thing);
        thing.init();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Thing<?>> T get(Class<T> clazz) {
        return (T) things.get(clazz);
    }
}
