package dev.rubikon.things.features;

import dev.rubikon.Rubikon;
import dev.rubikon.settings.Options;
import dev.rubikon.utils.Serializable;
import dev.rubikon.utils.Toggleable;
import dev.rubikon.utils.ChatUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;

/**
 * The base class for all features.
 * All features have to extend this class.
 */
public abstract class Feature implements Toggleable, Serializable<Feature> {
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Category category;
    @Getter
    @Setter
    private int keybind;
    @Getter
    private final Options options = new Options();
    @Getter
    private boolean toggled = false;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Feature(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Feature(String name, String description, Category category, int keybind) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keybind = keybind;
    }

    @Override
    public void toggle() {
        toggle(false);
    }

    @Override
    public void toggle(boolean sendMessage) {
        setToggled(!toggled, sendMessage);
    }

    private void setToggled(boolean toggled, boolean sendMessage) {
        this.toggled = toggled;

        if (toggled) {
            Rubikon.getEventPubSub().subscribe(this);
            onEnable();

            if (sendMessage)
                ChatUtils.sendMessage("Feature<highlight> %s <white>has been <#3df518>enabled<white>.", name);
        } else {
            onDisable();
            Rubikon.getEventPubSub().unsubscribe(this);

            if (sendMessage)
                ChatUtils.sendMessage("Feature<highlight> %s <white>has been <#eb3528>disabled<white>.", name);
        }
    }

    @Override
    public NbtCompound toNbtTag() {
        NbtCompound nbt = new NbtCompound();

        nbt.putString("name", name);
        nbt.putInt("keybind", keybind);
        nbt.putBoolean("toggled", toggled);
        nbt.put("options", options.toNbtTag());

        return nbt;
    }

    @Override
    public Feature fromNbtTag(NbtCompound nbtTag) {
        if (nbtTag.contains("keybind")) setKeybind(nbtTag.getInt("keybind"));
        if (nbtTag.contains("toggled")) setToggled(nbtTag.getBoolean("toggled"), false);
        if (nbtTag.contains("options")) options.fromNbtTag((NbtCompound) nbtTag.get("options"));

        return this;
    }
}
