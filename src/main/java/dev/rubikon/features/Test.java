package dev.rubikon.features;

import dev.rubikon.Rubikon;
import dev.rubikon.api.feature.AstractFeature;
import dev.rubikon.api.setting.impl.BooleanSetting;

public class Test extends AstractFeature {
    private final BooleanSetting setting = new BooleanSetting("test","test",true);

    @Override
    public void onEnable() {
        Rubikon.LOGGER.info("on");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Rubikon.LOGGER.info("off");
        super.onDisable();
    }
}
