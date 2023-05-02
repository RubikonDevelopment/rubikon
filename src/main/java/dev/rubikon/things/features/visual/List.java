package dev.rubikon.things.features.visual;

import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.things.features.Feature;
import dev.rubikon.things.features.FeatureCategory;
import io.github.nevalackin.radbus.Listen;
import org.lwjgl.glfw.GLFW;

// TODO CUSTOMIZATION
public class List extends Feature {
    public List() {
        super("List", "Shows the list of Toggled Modules", GLFW.GLFW_KEY_P, FeatureCategory.VISUAL);
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
