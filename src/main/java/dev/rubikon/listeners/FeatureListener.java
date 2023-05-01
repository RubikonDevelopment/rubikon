package dev.rubikon.listeners;

import dev.rubikon.api.feature.Feature;
import dev.rubikon.events.KeyPressEvent;
import dev.rubikon.stores.Stores;
import io.github.nevalackin.radbus.Listen;
import net.minecraft.client.MinecraftClient;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

/**
 * This class listens for key presses and toggles features accordingly.
 */
public class FeatureListener {
    @Listen
    public void onKeyPress(KeyPressEvent event) {
        if (event.action() != GLFW_PRESS) return;
        if (MinecraftClient.getInstance().currentScreen != null) return;

        for (Feature feature : Stores.FEATURE.values()) {
            if (feature.getKeybind() == event.key()) feature.toggle();
        }
    }
}
