package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import net.minecraft.nbt.NbtCompound;

public class StringOption extends Option<String> {
    public StringOption(String name, String description, String defaultValue) {
        super(name, description, defaultValue);
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
        return true;
    }
}
