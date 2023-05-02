package dev.rubikon.settings.types;

import com.google.common.collect.ImmutableList;
import dev.rubikon.Rubikon;
import dev.rubikon.settings.Option;
import net.minecraft.nbt.NbtCompound;

import java.awt.Color;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a color option.
 * @see Option
 * @see Color
 */
public class ColorOption extends Option<Color> {
    private static final List<String> COMMAND_SUGGESTIONS = ImmutableList.of("#FFFFFF", Rubikon.PRIMARY_COLOR);
    private final Predicate<Color> validator;

    public ColorOption(String name, String description, Color defaultValue) {
        this(name, description, defaultValue, null);
    }

    public ColorOption(String name, String description, Color defaultValue, Predicate<Color> validator) {
        super(name, description, defaultValue);

        this.validator = validator;
    }

    @Override
    public boolean parse(String value) {
        try {
            return super.parse(Color.decode(value.trim()));
        } catch (NumberFormatException e) {
            return super.parse((Object) null);
        }
    }

    @Override
    public List<String> commandSuggestions() {
        return COMMAND_SUGGESTIONS;
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.putInt("value", get().getRGB());

        return null;
    }

    @Override
    protected Color load(NbtCompound nbt) {
        if (nbt.contains("value"))
            set(new Color(nbt.getInt("value")));

        return get();
    }

    @Override
    public boolean isValid(Color value) {
        return validator == null || validator.test(value);
    }
}
