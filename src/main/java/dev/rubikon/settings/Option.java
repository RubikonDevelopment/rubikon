package dev.rubikon.settings;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an options that can be changed by the user.
 * @param <V> The type of the option's value.
 */
public class Option<V> {
    @Getter
    @Setter
    private V value;
    @Getter
    private final String name, tooltip;

    public Option(String name, String tooltip, V initialValue) {
        this.tooltip = tooltip;
        this.name = name;
        this.value = initialValue;
    }
}
