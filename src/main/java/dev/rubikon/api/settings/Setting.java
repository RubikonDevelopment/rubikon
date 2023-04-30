package dev.rubikon.api.settings;

import lombok.Getter;
import lombok.Setter;

public class Setting<Value> {
    @Getter
    @Setter
    private Value value;
    @Getter
    private final String name, tooltip;

    public Setting(String name, String tooltip, Value initialvalue) {
        this.tooltip = tooltip;
        this.name = name;
        this.value = initialvalue;
    }
}
