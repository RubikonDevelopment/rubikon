package dev.rubikon.settings.types;

import dev.rubikon.settings.ListOption;
import dev.rubikon.settings.Option;
import dev.rubikon.utils.NbtUtils;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StringListOption extends Option<List<String>> implements ListOption<String> {
    private final Predicate<List<String>> validator;

    public StringListOption(String name, String description, List<String> defaultValue) {
        this(name, description, defaultValue, null);
    }

    public StringListOption(String name, String description, List<String> defaultValue, Predicate<List<String>> validator) {
        super(name, description, defaultValue);

        this.validator = validator;
    }

    @Override
    public boolean parse(String value) {
        return super.parse(Arrays.stream(value.trim().split(" ")).toList());
    }

    @Override
    public List<String> commandSuggestions() {
        return Stream.concat(get().stream(), getDefaultValue().stream()).toList();
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.put("value", NbtUtils.stringListToTag(get()));

        return nbt;
    }

    @Override
    protected List<String> load(NbtCompound nbt) {
        if (nbt.contains("value")) {
            get().clear();

            NbtList list = nbt.getList("value", 8);
            for (NbtElement element : list) {
                get().add(element.asString());
            }
        }

        return get();
    }

    @Override
    public boolean isValid(List<String> value) {
        return validator == null || validator.test(value);
    }
}
