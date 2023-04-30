package dev.rubikon.api.setting.impl;

import dev.rubikon.api.setting.AbstractSetting;

public class ArraySetting extends AbstractSetting<String> {
    private final String[] values;
    public ArraySetting(String name, String tooltip, String initialvalue, String[] values) {
        super(name, tooltip, initialvalue);
        this.values = values;
    }


    public void setValueByIndex(int index) {
        super.setValue(values[index]);
    }
}
