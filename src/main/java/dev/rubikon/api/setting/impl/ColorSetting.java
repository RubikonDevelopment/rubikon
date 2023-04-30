package dev.rubikon.api.setting.impl;

import dev.rubikon.api.setting.AbstractSetting;

import java.awt.*;

public class ColorSetting extends AbstractSetting<Color> {
    public ColorSetting(String name, String tooltip, Color initialvalue) {
        super(name, tooltip, initialvalue);
    }


    @Override
    public Color getValue() {
        return super.getValue();
    }
    public float[] getFloatValue() {
        return new float[]{settingvalue.getRed() / 255.f,settingvalue.getGreen()/ 255.f,settingvalue.getGreen()/ 255.f,settingvalue.getAlpha()/ 255.f};
    }

}
