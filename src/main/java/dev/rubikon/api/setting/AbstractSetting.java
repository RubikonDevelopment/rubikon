package dev.rubikon.api.setting;

public class AbstractSetting<Value> {
    public Value settingvalue;

    private final String name,tooltip;

    public AbstractSetting(String name,String tooltip,Value initialvalue) {
        this.tooltip = tooltip;
        this.name = name;
        this.settingvalue = initialvalue;
    }

    public void setValue(Value value) {
        this.settingvalue = value;
    }

    public Value getValue() {
        return settingvalue;
    }
    public String getName() {
        return name;
    }

    public String getTooltip() {
        return tooltip;
    }
}
