package dev.rubikon.settings.types;

import java.awt.*;

public class ColorOption extends dev.rubikon.settings.Option<Color> {
    public ColorOption(String name, String tooltip, Color value) {
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
