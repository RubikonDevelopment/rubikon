package dev.rubikon.api.feature;

import dev.rubikon.Rubikon;
import dev.rubikon.api.commons.Toggleable;
import dev.rubikon.api.settings.Setting;
import dev.rubikon.utils.ChatUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * a object that can be toggled on/ off
 */
public abstract class Feature implements Toggleable {
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final int keybind;
    @Getter
    private ArrayList<Setting> settings = new ArrayList<>();
    @Getter
    @Setter
    private boolean toggled = false;

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

            ChatUtils.sendMessage("Feature (highlight)%s (default)has been enabled.", name);
        } else {
            onDisable();
            Rubikon.getEventPubSub().unsubscribe(this);

            ChatUtils.sendMessage("Feature (highlight)%s (default)has been disabled.", name);
        }
    }
}
