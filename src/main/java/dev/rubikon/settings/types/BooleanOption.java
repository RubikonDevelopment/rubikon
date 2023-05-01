package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;

public class BooleanOption extends Option<Boolean> {
    public BooleanOption(String name, String tooltip, Boolean value) {
        super(name, tooltip, value);
    }
}
