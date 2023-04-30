package dev.rubikon.api.settings.types;

import dev.rubikon.api.settings.Setting;

import java.awt.*;

public class ColorSetting extends Setting<Color> {
    public ColorSetting(String name, String tooltip, Color value) {
        super(name, tooltip, value);
    }

    @Override
    public Color getValue() {
        return super.getValue();
    }

    public float[] getFloatValue() {
        return new float[]{
                getValue().getRed() / 255.f,
                getValue().getGreen()/ 255.f,
                getValue().getGreen()/ 255.f,
                getValue().getAlpha()/ 255.f
        };
    }
}
