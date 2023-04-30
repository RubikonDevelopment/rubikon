package dev.rubikon.api.settings.types;

import dev.rubikon.api.settings.Setting;

public class ArraySetting extends Setting<String> {
    private final String[] values;

    public ArraySetting(String name, String tooltip, String value, String[] values) {
        super(name, tooltip, value);
        this.values = values;
    }

    public void setValueByIndex(int index) {
        super.setValue(values[index]);
    }
}
