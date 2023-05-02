package dev.rubikon.settings;

import dev.rubikon.utils.Serializable;
import dev.rubikon.utils.Store;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.*;

/**
 * Represents a group of options.
 * <p>
 *     Option groups are used to group options together.
 * </p>
 * @see Option
 * @see Options
 */
public class OptionGroup implements Serializable, Store<String, Option> {
    @Getter
    private final String name;
    /**
     * Whether the group is expanded or not.
     */
    @Getter
    @Setter
    private boolean expanded;

    @Getter
    private final Map<String, Option<?>> options = new HashMap<>();

    public OptionGroup(String name, boolean expanded) {
        this.name = name;
        this.expanded = expanded;
    }

    /**
     * Adds an option to the group.
     * <p>
     *     This method is used to add an option to the group.
     * </p>
     * @param option The option to add.
     * @return The added option.
     */
    public <T> Option<T> add(Option<T> option) {
        options.put(option.getName(), option);

        return option;
    }

    /**
     * Finds an option by its name.
     * @param name The name of the option.
     * @return The found option if found, `null` otherwise.
     */
    public Option<?> find(String name) {
        return options.get(name);
    }

    /**
     * @return A collection of all option names.
     */
    public Collection<String> names() {
        return options.keySet();
    }

    /**
     * @return A collection of all options.
     */
    public Collection<Option<?>> all() {
        return options.values();
    }

    /**
     * Serializes the option group to a nbt tag.
     * @return The nbt tag.
     *
     * @see #fromNbtTag(NbtCompound)
     */
    @Override
    public NbtCompound toNbtTag() {
        NbtCompound nbt = new NbtCompound();

        nbt.putString("name", name);
        nbt.putBoolean("expanded", expanded);

        NbtList options = new NbtList();
        for (Option<?> option : this.options.values()) {
            options.add(option.toNbtTag());
        }
        nbt.put("options", options);

        return nbt;
    }

    /**
     * Deserializes the nbtag to an option group.
     * @param nbt The nbt tag.
     * @return The option group.
     */
    @Override
    public OptionGroup fromNbtTag(NbtCompound nbt) {
        if (nbt.contains("expanded")) expanded = nbt.getBoolean("expanded");
        if (!nbt.contains("options")) return this;

        NbtList options = nbt.getList("options", 10);

        for (NbtElement element : options) {
            NbtCompound compound = (NbtCompound) element;

            Option<?> option = find(compound.getString("name"));
            option.fromNbtTag(compound);
        }

        return this;
    }
}
