package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;

public class ArrayOption extends Option<String> {
    private final String[] values;

    public ArrayOption(String name, String tooltip, String value, String[] values) {
        super(name, tooltip, value);
        this.values = values;
    }

    public void setValueByIndex(int index) {
        super.setValue(values[index]);
    }
}
