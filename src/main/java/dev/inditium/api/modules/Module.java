package dev.inditium.api.modules;

import com.google.gson.annotations.Expose;
import dev.inditium.Inditium;
import dev.inditium.api.serialization.Serializable;
import lombok.Getter;
import lombok.Setter;

public abstract class Module implements Serializable<Module> {
    @Expose
    @Getter
    @Setter
    private boolean enabled;

    public Module() {
        this.load();
    }

    public void onInitialize() {}

    @Override
    public void save() {
        Inditium.STORAGE.MODULES
                .save(
                        this.getClass().getSimpleName() + ".json", serialize()
                );
    }

    @Override
    public void load() {
        String data = Inditium.STORAGE.MODULES
                .get(
                        this.getClass().getSimpleName() + ".json"
                );
        if (data == null) return;

        this.setEnabled(deserialize(data).isEnabled());
    }
}
