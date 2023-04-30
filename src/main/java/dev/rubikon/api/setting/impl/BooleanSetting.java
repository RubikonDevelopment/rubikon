package dev.rubikon.api.setting.impl;

import dev.rubikon.api.setting.AbstractSetting;

public class BooleanSetting extends AbstractSetting<Boolean> {
    public BooleanSetting(String name, String tooltip, Boolean initialvalue) {
        super(name, tooltip, initialvalue);
    }

}
