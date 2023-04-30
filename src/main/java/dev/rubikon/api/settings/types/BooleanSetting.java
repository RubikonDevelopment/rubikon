package dev.rubikon.api.settings.types;

import dev.rubikon.api.settings.Setting;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, String tooltip, Boolean value) {
        super(name, tooltip, value);
    }
}
