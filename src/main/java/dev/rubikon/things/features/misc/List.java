package dev.rubikon.things.features.misc;

import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.things.features.Feature;
import io.github.nevalackin.radbus.Listen;
import org.lwjgl.glfw.GLFW;

// TODO CUSTOMIZATION
public class List extends Feature {
    public List() {
        super("List", "Shows the list of Toggled Modules", GLFW.GLFW_KEY_P);
    }

    @Override
    public void onEnable() {
    }

    @Listen
    public void onRender(ScreenRenderEvent event) {
    // TODO finish
    }

    @Override
    public void onDisable() {

    }
}
