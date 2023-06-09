package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import lombok.Getter;
import net.minecraft.nbt.NbtCompound;

/**
 * Represents an integer option.
 * @see Option
 * @see Integer
 */
public class IntOption extends Option<Integer> {
    @Getter
    private final int min, max;

    public IntOption(String name, String description, int value, int min, int max) {
        super(name, description, value);

        this.min = min;
        this.max = max;
    }

    @Override
    public boolean parse(String value) {
        try {
            return super.parse(Integer.parseInt(value.trim()));
        } catch (NumberFormatException e) {
            return super.parse((Object) null);
        }
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.putInt("value", get());

        return nbt;
    }

    @Override
    protected Integer load(NbtCompound nbt) {
        if (nbt.contains("value"))
            set(nbt.getInt("value"));

        return get();
    }

    @Override
    public boolean isValid(Integer value) {
        return value >= min && value <= max;
    }
}
