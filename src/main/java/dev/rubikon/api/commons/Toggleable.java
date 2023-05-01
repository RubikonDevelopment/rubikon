package dev.rubikon.api.commons;


/**
 * This interface is used to mark features that can be toggled on and off.
 */
public interface Toggleable {
    void toggle();
    void onEnable();
    void onDisable();
}
