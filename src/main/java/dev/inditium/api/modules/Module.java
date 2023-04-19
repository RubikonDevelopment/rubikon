package dev.inditium.api.modules;

import com.google.gson.annotations.Expose;
import dev.inditium.api.serialization.Serializable;
import lombok.Getter;
import lombok.Setter;

public abstract class Module implements Serializable {
    @Expose
    @Getter
    @Setter
    private boolean enabled;

    public void onInitialize() {}
}
