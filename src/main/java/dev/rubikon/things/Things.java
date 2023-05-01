package dev.rubikon.things;

import dev.rubikon.Rubikon;
import dev.rubikon.events.DisconnectEvent;
import dev.rubikon.things.commands.Commands;
import dev.rubikon.things.features.Features;

import java.util.HashMap;
import java.util.Map;

public class Things {
    private static final Map<Class<? extends Thing>, Thing<?>> things = new HashMap<>();

    public static void init() {
        add(new Features());
        add(new Commands());

        Rubikon.getEventPubSub().subscribe(DisconnectEvent.class, (event) -> {
            things.values().forEach(Thing::save);
        });
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
