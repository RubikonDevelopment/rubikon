package dev.rubikon.settings.types;

import com.google.common.collect.ImmutableList;
import dev.rubikon.settings.Option;
import net.minecraft.nbt.NbtCompound;

import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a boolean option.
 * @see Option
 * @see Boolean
 */
public class BoolOption extends Option<Boolean> {
    private static final List<String> COMMAND_SUGGESTIONS = ImmutableList.of("true", "false", "toggle");
    private final Predicate<Boolean> validator;

    public BoolOption(String name, String description, Boolean defaultValue) {
        this(name, description, defaultValue, null);
    }

    public BoolOption(String name, String description, Boolean defaultValue, Predicate<Boolean> validator) {
        super(name, description, defaultValue);

        this.validator = validator;
    }

    @Override
    public boolean parse(String value) {
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1") || value.equalsIgnoreCase("on"))
            return super.parse(true);
        else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("0") || value.equalsIgnoreCase("off"))
            return super.parse(false);
        else if (value.equalsIgnoreCase("toggle"))
            return super.parse(!get());

        return super.parse((Object) null);
    }

    @Override
    public List<String> commandSuggestions() {
        return COMMAND_SUGGESTIONS;
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.putBoolean("value", get());

        return nbt;
    }

    @Override
    protected Boolean load(NbtCompound nbt) {
        if (nbt.contains("value"))
            set(nbt.getBoolean("value"));

        return get();
    }

    @Override
    public boolean isValid(Boolean value) {
        return validator == null || validator.test(value);
    }
}
