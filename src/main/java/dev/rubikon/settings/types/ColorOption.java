package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import net.minecraft.nbt.NbtCompound;

import java.awt.Color;

public class ColorOption extends Option<Color> {
    public ColorOption(String name, String description, Color defaultValue) {
        super(name, description, defaultValue);
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
        return true;
    }
}
