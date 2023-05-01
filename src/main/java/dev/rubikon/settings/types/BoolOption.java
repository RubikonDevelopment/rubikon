package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import net.minecraft.nbt.NbtCompound;

public class BoolOption extends Option<Boolean> {
    public BoolOption(String name, String description, Boolean defaultValue) {
        super(name, description, defaultValue);
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
        return true;
    }
}
