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
                Option<T> option = getDefaultValue().stream().findFirst().filter(o -> o.getName().equals(name)).orElse(null);

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
