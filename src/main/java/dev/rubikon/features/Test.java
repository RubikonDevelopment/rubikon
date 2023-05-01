package dev.rubikon.features;

import dev.rubikon.Rubikon;
import dev.rubikon.api.feature.Feature;
import dev.rubikon.api.settings.types.BooleanOption;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_K;

public class Test extends Feature {
    private final BooleanOption setting = new BooleanOption("test","test",true);

    public Test() {
        super("Test", "Developer feature for testing purposes", GLFW_KEY_K);

        getSettings().add(setting);
    }

    @Override
    public void onEnable() {
        Rubikon.LOGGER.info("on");
    }

    @Override
    public void onDisable() {
        Rubikon.LOGGER.info("off");
    }
}
