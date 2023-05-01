package dev.rubikon.api.feature;

import dev.rubikon.Rubikon;
import dev.rubikon.api.commons.Toggleable;
import dev.rubikon.api.settings.Option;
import dev.rubikon.utils.ChatUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

/**
 * The base class for all features.
 * All features have to extend this class.
 */
public abstract class Feature implements Toggleable {
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final int keybind;
    @Getter
    private ArrayList<Option> settings = new ArrayList<>();
    @Getter
    @Setter
    private boolean toggled = false;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Feature(String name, String description, int keybind) {
        this.name = name;
        this.description = description;
        this.keybind = keybind;
    }

    @Override
    public void toggle() {
        toggled = !toggled;

        if (toggled) {
            Rubikon.getEventPubSub().subscribe(this);
            onEnable();

            ChatUtils.sendMessage("Feature<highlight> %s <white>has been <#3df518>enabled<white>.", name);
        } else {
            onDisable();
            Rubikon.getEventPubSub().unsubscribe(this);

            ChatUtils.sendMessage("Feature<highlight> %s <white>has been <#eb3528>disabled<white>.", name);
        }
    }
}
