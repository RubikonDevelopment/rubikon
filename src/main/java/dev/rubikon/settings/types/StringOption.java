package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import net.minecraft.nbt.NbtCompound;

import java.util.function.Predicate;

/**
 * Represents a string option.
 * @see Option
 * @see String
 */
public class StringOption extends Option<String> {
    private final Predicate<String> validator;

    public StringOption(String name, String description, String defaultValue) {
        this(name, description, defaultValue, null);
    }

    public StringOption(String name, String description, String defaultValue, Predicate<String> validator) {
        super(name, description, defaultValue);

        this.validator = validator;
    }

    @Override
    public boolean parse(String value) {
        return super.parse((Object) value.trim());
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.putString("value", get());

        return nbt;
    }

    @Override
    protected String load(NbtCompound nbt) {
        if (nbt.contains("value"))
            set(nbt.getString("value"));

        return get();
    }

    @Override
    public boolean isValid(String value) {
        return validator == null || validator.test(value);
    }
}
