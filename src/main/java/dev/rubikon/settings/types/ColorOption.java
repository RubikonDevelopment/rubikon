package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import net.minecraft.nbt.NbtCompound;

import java.awt.Color;
import java.util.function.Predicate;

public class ColorOption extends Option<Color> {
    private final Predicate<Color> validator;

    public ColorOption(String name, String description, Color defaultValue) {
        this(name, description, defaultValue, null);
    }

    public ColorOption(String name, String description, Color defaultValue, Predicate<Color> validator) {
        super(name, description, defaultValue);

        this.validator = validator;
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
