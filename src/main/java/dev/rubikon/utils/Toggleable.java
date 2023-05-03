package dev.rubikon.utils;


/**
 * This interface is used to mark features that can be toggled on and off.
 */
public interface Toggleable {
    void toggle();
    void toggle(boolean sendMessage);
    default void onEnable() {};
    default void onDisable() {};
}
