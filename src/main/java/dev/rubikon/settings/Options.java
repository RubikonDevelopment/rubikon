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

/**
 * Represents a group of option groups.
 * @see OptionGroup
 */
public class Options implements Serializable<Options>, Store<String, OptionGroup> {
    private OptionGroup defaultGroup;
    private final Map<String, OptionGroup> groups = new HashMap<>();

    /**
     * @return the default group.
     */
    public OptionGroup defaultGroup() {
        if (defaultGroup == null) defaultGroup = create("General");

        return defaultGroup;
    }

    /**
     * Creates a new option group.
     * @param name The name of the group.
     * @param expanded Whether the group is expanded by default.
     * @return The created group.
     */
    public OptionGroup create(String name, boolean expanded) {
        OptionGroup optionGroup = new OptionGroup(name, expanded);
        add(optionGroup);

        return optionGroup;
    }

    /**
     * Creates a new option group.
     * @param name The name of the group.
     * @return The created group.
     *
     * @see #create(String, boolean)
     */
    public OptionGroup create(String name) {
        return create(name, true);
    }

    /**
     * Adds an option group to the store.
     * <p>
     *     This method is used to add an option group to the store.
     *     If an option group with the same name already exists, it will be overwritten.
     *     If you want to add an option to an existing group, use {@link OptionGroup#add(Option)}.
     *     If you want to add an option to the default group, use {@link #defaultGroup()} and {@link OptionGroup#add(Option)}.
     * </p>
     * @param group The option group to add.
     */
    @Override
    public void add(OptionGroup group) {
        groups.put(group.getName(), group);
    }

    /**
     * Finds an option group by its name.
     * @param name The name of the option group.
     * @return The found option group if found, `null` otherwise.
     */
    @Override
    public OptionGroup find(String name) {
        return groups.get(name);
    }

    /**
     * Finds an option by its name.
     * <p>
     *     This method is used to find an option by its name.
     *     It will search through all option groups.
     *     If you want to find an option in a specific group, use {@link OptionGroup#find(String)}.
     *     If you want to find an option in the default group, use {@link #defaultGroup()} and {@link OptionGroup#find(String)}.
     * </p>
     * @param name The name of the option.
     * @return The found option if found, `null` otherwise.
     */
    public Option<?> findOption(String name) {
        for (OptionGroup group : groups.values()) {
            Option<?> option = group.find(name);
            if (option != null) return option;
        }

        return null;
    }

    /**
     * @return A collection of all option group names.
     */
    @Override
    public Collection<String> names() {
        return groups.keySet();
    }

    /**
     * <p>
     *     This method is used to get all option names from all option groups.
     * </p>
     * @return A collection of all option names.
     */
    public Collection<String> optionNames() {
        Collection<String> names = new ArrayList<>();

        for (OptionGroup group : groups.values()) {
            names.addAll(group.names());
        }

        return names;
    }

    /**
     * @return A collection of all option groups.
     */
    @Override
    public Collection<OptionGroup> all() {
        return groups.values();
    }

    /**
     * Serializes the store to a nbt tag.
     * @return The nbt tag.
     *
     * @see #fromNbtTag(NbtCompound)
     */
    @Override
    public NbtCompound toNbtTag() {
        NbtCompound nbt = new NbtCompound();

        nbt.put("groups", listToTag(groups.values()));

        return nbt;
    }

    /**
     * Deserializes the store from a nbt tag.
     * @param nbt The nbt tag.
     * @return The store.
     */
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
