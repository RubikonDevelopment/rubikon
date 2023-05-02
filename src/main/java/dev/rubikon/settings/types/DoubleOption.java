package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import lombok.Getter;
import net.minecraft.nbt.NbtCompound;

public class DoubleOption extends Option<Double> {
    @Getter
    private final double min, max;

    public DoubleOption(String name, String description, double value, double min, double max) {
        super(name, description, value);

        this.min = min;
        this.max = max;
    }

    @Override
    public boolean parse(String value) {
        try {
            return super.parse(Double.parseDouble(value.trim()));
        } catch (NumberFormatException e) {
            return super.parse((Object) null);
        }
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.putDouble("value", get());

        return nbt;
    }

    @Override
    protected Double load(NbtCompound nbt) {
        if (nbt.contains("value"))
            set(nbt.getDouble("value"));

        return get();
    }

    @Override
    public boolean isValid(Double value) {
        return value >= min && value <= max;
    }
}
