package dev.rubikon.things;

import dev.rubikon.Rubikon;
import dev.rubikon.events.DisconnectEvent;
import dev.rubikon.things.commands.Commands;
import dev.rubikon.things.features.Features;
import net.minecraft.nbt.NbtCompound;

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

    /**
     * Initializes all things.
     * <p>
     *     This method is called in the {@link Rubikon#onInitialize()}  method.
     * </p>
     *
     * @see Thing#init()
     */
    public static void init() {
        add(new Features());
        add(new Commands());

        Rubikon.getEventPubSub().subscribe(DisconnectEvent.class, event -> save());
    }

    /**
     * Loads all things.
     *
     * @see Thing#load()
     * @see Thing#fromNbtTag(NbtCompound)
     */
    public static void load() {
        things.values().forEach(Thing::load);
    }

    /**
     * Saves all things.
     *
     * @see Thing#save()
     * @see Thing#toNbtTag()
     */
    public static void save() {
        things.values().forEach(Thing::save);
    }

    /**
     * Adds a thing.
     * <p>
     *     This method is used to add a thing. This method also calls the {@link Thing#init()} method.
     *     <br>
     *     <br>
     *     This method is called in the {@link #init()} method.
     * </p>
     * @param thing The thing to add.
     *
     * @see Thing
     */
    public static void add(Thing<?> thing) {
        things.put(thing.getClass(), thing);
        thing.init();
    }

    /**
     * Gets a thing.
     * @param clazz The thing's class.
     * @return The thing.
     *
     * @throws ClassCastException If the thing is not found.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Thing<?>> T get(Class<T> clazz) {
        return (T) things.get(clazz);
    }
}
