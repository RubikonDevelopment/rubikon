package dev.inditium.api.modules;

public interface Toggleable {
    default void toggle() {
        Module module = (Module) this;

        if (module.isEnabled()) {
            onDisable();
            module.setEnabled(false);
        } else {
            onEnable();
            module.setEnabled(true);
        }
    };

    void onEnable();
    void onDisable();
}
