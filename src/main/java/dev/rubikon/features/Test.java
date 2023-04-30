package dev.rubikon.features;

import dev.rubikon.Rubikon;
import dev.rubikon.api.feature.AstractFeature;

public class Test extends AstractFeature {

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
