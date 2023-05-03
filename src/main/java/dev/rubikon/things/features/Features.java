package dev.rubikon.things.features;

import dev.rubikon.Rubikon;
import dev.rubikon.events.KeyPressEvent;
import dev.rubikon.utils.Store;
import dev.rubikon.things.Thing;
import dev.rubikon.things.Things;
import dev.rubikon.things.features.misc.*;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.Collection;
import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

/**
 * Represents the features' thing.
 * This thing is responsible for registering and managing all features.
 * <p>
 *     All features are stored in a HashMap for faster finding.
 *     <br>
 *     <br>
 *     To register a feature, you need to call the {@link #add(Feature)} method.
 *     <br>
 *     <br>
 *     This is singleton class, so you can access it using the {@link #get()} method.
 * </p>
 *
 * @see Feature
 * @see Store
 * @see Thing
 */
public class Features extends Thing<Features> implements Store<String, Feature> {
    @Getter
    private final HashMap<String, Feature> features = new HashMap<>(); // HashMap for faster finding

    public Features() {
        super("features");
    }

    /**
     * Initializes all features.
     * <p>
     *     This method subscribes to the {@link KeyPressEvent} event.
     *     <br>
     *     <br>
     *     This method is called in the {@link Things#init()} method.
     * </p>
     *
     * @see Things#init()
     */
    @Override
    public void init() {
        add(new Sprint());
        add(new Test());
        add(new Logo());

        Rubikon.getEventPubSub().subscribe(KeyPressEvent.class, (event) -> {
            if (event.action() != GLFW_PRESS) return;
            if (MinecraftClient.getInstance().currentScreen != null) return;

            for (Feature feature : Features.get().all()) {
                if (feature.getKeybind() == event.key()) feature.toggle();
            }
        });
    }

    /**
     * @return The features thing instance.
     */
    public static Features get() {
        return Things.get(Features.class);
    }

    /**
     * Registers a feature.
     * <p>
     *     This method is used to register a feature.
     *     <br>
     *     <br>
     *     This method is called in the {@link #init()} method.
     * </p>
     * @param feature The feature to register.
     *
     * @see Feature
     */
    public void add(Feature feature) {
        features.put(feature.getName(), feature);
    }

    /**
     * Finds a feature by its name.
     * @param name The name of the feature.
     * @return The feature if found, `null` otherwise.
     */
    public Feature find(String name) {
        return features.get(name);
    }

    /**
     * @return A collection of all feature names.
     */
    public Collection<String> names() {
        return features.keySet();
    }

    /**
     * @return A collection of all features.
     */
    public Collection<Feature> all() {
        return features.values();
    }

    @Override
    public NbtCompound toNbtTag() {
        NbtCompound nbt = new NbtCompound();
        NbtList list = new NbtList();

        for (Feature feature : all()) {
            list.add(feature.toNbtTag());
        }

        nbt.put("features", list);

        return nbt;
    }

    @Override
    public Features fromNbtTag(NbtCompound nbt) {
        NbtList list = nbt.getList("features", 10);
        for (NbtElement element : list) {
            NbtCompound compound = (NbtCompound) element;
            Feature feature = features.get(compound.getString("name"));

            feature.fromNbtTag(compound);
        }

        return this;
    }
}
