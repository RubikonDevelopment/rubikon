package dev.rubikon.things;

import dev.rubikon.Rubikon;
import dev.rubikon.events.DisconnectEvent;
import dev.rubikon.things.commands.Commands;
import dev.rubikon.things.features.Features;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the things.
 * This class is responsible for registering and managing all things.
 * <p>
 *     All things are stored in a HashMap for faster finding.
 *     <br>
 *     <br>
 *     To register a thing, you need to call the {@link #add(Thing)} method.
 *     <br>
 *     <br>
 *     You can access a thing using the {@link #get(Class)} method or directly using `get` method in `thing`.
 * </p>
 *
 * @see Thing
 */
public class Things {
    private static final Map<Class<? extends Thing>, Thing<?>> things = new HashMap<>();

    public static void init() {
        add(new Features());
        add(new Commands());

        Rubikon.getEventPubSub().subscribe(DisconnectEvent.class, event -> save());
    }

    public static void load() {
        things.values().forEach(Thing::load);
    }

    public static void save() {
        things.values().forEach(Thing::save);
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
