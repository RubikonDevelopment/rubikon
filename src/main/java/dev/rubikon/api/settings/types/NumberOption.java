package dev.rubikon.api.settings.types;

import dev.rubikon.api.settings.Option;
import lombok.Getter;
import net.minecraft.util.math.MathHelper;

public class NumberOption extends Option<Number> {
    @Getter
    private final Number minValue, maxValue;

    public NumberOption(String name, String tooltip, Number minValue, Number value, Number maxValue) {
        super(name, tooltip, value);

        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void setValue(Number number) {
        super.setValue(clamp(number));
    }

    private float clamp(Number value) {
        return MathHelper.clamp(value.intValue(), minValue.intValue(), maxValue.intValue());
    }
}
