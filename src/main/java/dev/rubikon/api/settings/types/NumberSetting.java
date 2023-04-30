package dev.rubikon.api.settings.types;

import dev.rubikon.api.settings.Setting;
import lombok.Getter;
import net.minecraft.util.math.MathHelper;

public class NumberSetting extends Setting<Number> {
    @Getter
    private final Number minValue, maxValue;

    public NumberSetting(String name, String tooltip, Number minValue, Number value, Number maxValue) {
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
