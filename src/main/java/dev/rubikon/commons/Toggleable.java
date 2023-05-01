package dev.rubikon.commons;


/**
 * This interface is used to mark features that can be toggled on and off.
 */
public interface Toggleable {
    void toggle();
    default void onEnable() {};
    default void onDisable() {};
}
