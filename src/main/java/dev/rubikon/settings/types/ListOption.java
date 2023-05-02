package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import dev.rubikon.utils.NbtUtils;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;

public class ListOption<T> extends Option<List<Option<T>>> {
    public ListOption(String name, String description, List<Option<T>> defaultValue) {
        super(name, description, defaultValue);
    }

    @Override
    public boolean parse(String value) {
        String[] values = value.trim().split(" ");
        List<Option<T>> options = new ArrayList<>();

        for (String val : values) {
            Option<T> option = getDefaultValue().stream().filter(o -> o.getDefaultValue().equals(val)).findFirst().orElse(null);

            if (option != null)
                options.add(option);
        }

        return options.size() == 0 ? super.parse((Object) null) : super.parse(options);
    }

    @Override
    public List<String> commandSuggestions() {
        return getDefaultValue().stream().map(o -> o.getDefaultValue().toString()).toList();
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.put("value", NbtUtils.listToTag(get()));

        return nbt;
    }

    @Override
    protected List<Option<T>> load(NbtCompound nbt) {
        if (nbt.contains("value")) {
            NbtList list = nbt.getList("value", 10);
            List<Option<T>> value = new ArrayList<>();

            for (NbtElement element : list) {
                NbtCompound compound = (NbtCompound) element;
                String name = compound.getString("name");
                Option<T> option = getDefaultValue().stream().filter(o -> o.getName().equals(name)).findFirst().orElse(null);

                if (option != null)
                    value.add(option);
            }

            set(value);
        }

        return get();
    }

    @Override
    public boolean isValid(List<Option<T>> value) {
        for (Option<T> option : value) {
            if (!option.isValid(option.get()))
                return false;
        }

        return true;
    }
}
