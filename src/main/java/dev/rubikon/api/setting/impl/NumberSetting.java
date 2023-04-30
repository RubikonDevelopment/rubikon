package dev.rubikon.api.setting.impl;

import dev.rubikon.api.setting.AbstractSetting;
import net.minecraft.util.math.MathHelper;

public class NumberSetting extends AbstractSetting<Number> {

    private Number minValue,maxValue;
    public NumberSetting(String name, String tooltip,Number minValue, Number initialvalue, Number maxValue) {
        super(name,tooltip,initialvalue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }


    public Number getMaxValue() {
        return maxValue;
    }

    public Number getMinValue() {
        return minValue;
    }

    @Override
    public void setValue(Number number) {
        super.setValue(clamp(number));
    }

    private float clamp(Number value) {
        return MathHelper.clamp(value.intValue(),minValue.intValue(),maxValue.intValue());
    }
}
