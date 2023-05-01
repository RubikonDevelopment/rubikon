package dev.rubikon.settings;

import dev.rubikon.utils.Serializable;
import dev.rubikon.utils.Store;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.*;

public class OptionGroup implements Serializable, Store<String, Option> {
    @Getter
    private final String name;
    @Getter
    @Setter
    private boolean expanded;

    @Getter
    private final Map<String, Option<?>> options = new HashMap<>();

    public OptionGroup(String name, boolean expanded) {
        this.name = name;
        this.expanded = expanded;
    }

    public <T> Option<T> add(Option<T> option) {
        options.put(option.getName(), option);

        return option;
    }

    public Option<?> find(String name) {
        return options.get(name);
    }

    public Collection<String> names() {
        return options.keySet();
    }

    public Collection<Option<?>> all() {
        return options.values();
    }

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
