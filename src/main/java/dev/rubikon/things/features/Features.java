package dev.rubikon.things.features;

import dev.rubikon.Rubikon;
import dev.rubikon.events.KeyPressEvent;
import dev.rubikon.things.features.visual.Logo;
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
import java.util.stream.Collectors;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class Features extends Thing<Features> implements Store<String, Feature> {
    @Getter
    private final HashMap<String, Feature> features = new HashMap<>(); // HashMap for faster finding

    public Features() {
        super("features");
    }

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

    public static Features get() {
        return Things.get(Features.class);
    }

    public void add(Feature feature) {
        features.put(feature.getName(), feature);
    }

    public Feature find(String name) {
        return features.get(name);
    }

    public Collection<String> names() {
        return features.keySet();
    }

    public Collection<Feature> all() {
        return features.values();
    }

    public Collection<Feature> findInCategory(FeatureCategory category) {
        return features.values().stream().filter(feature -> feature.getFeatureCategory() == category).collect(Collectors.toList());
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
