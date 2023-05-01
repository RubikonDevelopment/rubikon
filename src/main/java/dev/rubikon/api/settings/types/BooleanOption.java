package dev.rubikon.api.settings.types;

import dev.rubikon.api.settings.Option;

public class BooleanOption extends Option<Boolean> {
    public BooleanOption(String name, String tooltip, Boolean value) {
        super(name, tooltip, value);
    }
}
