package dev.rubikon.settings;

import dev.rubikon.utils.Serializable;
import dev.rubikon.utils.Store;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static dev.rubikon.utils.NbtUtils.listToTag;

public class Options implements Serializable<Options>, Store<String, OptionGroup> {
    private OptionGroup defaultGroup;
    private final Map<String, OptionGroup> groups = new HashMap<>();

    public OptionGroup defaultGroup() {
        if (defaultGroup == null) defaultGroup = create("General");

        return defaultGroup;
    }

    public OptionGroup create(String name, boolean expanded) {
        OptionGroup optionGroup = new OptionGroup(name, expanded);
        add(optionGroup);

        return optionGroup;
    }

    public OptionGroup create(String name) {
        return create(name, true);
    }

    @Override
    public void add(OptionGroup item) {
        groups.put(item.getName(), item);
    }

    @Override
    public OptionGroup find(String name) {
        return groups.get(name);
    }

    public Option<?> findOption(String name) {
        for (OptionGroup group : groups.values()) {
            Option<?> option = group.find(name);
            if (option != null) return option;
        }

        return null;
    }

    @Override
    public Collection<String> names() {
        return groups.keySet();
    }

    public Collection<String> optionNames() {
        Collection<String> names = new ArrayList<>();

        for (OptionGroup group : groups.values()) {
            names.addAll(group.names());
        }

        return names;
    }

    @Override
    public Collection<OptionGroup> all() {
        return groups.values();
    }

    @Override
    public NbtCompound toNbtTag() {
        NbtCompound nbt = new NbtCompound();

        nbt.put("groups", listToTag(groups.values()));

        return nbt;
    }

    @Override
    public Options fromNbtTag(NbtCompound nbt) {
        NbtList groups = nbt.getList("groups", 10);

        for (NbtElement element : groups) {
            NbtCompound compound = (NbtCompound) element;

            OptionGroup optionGroup = find(compound.getString("name"));
            optionGroup.fromNbtTag(compound);
        }

        return this;
    }
}
