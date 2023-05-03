package dev.rubikon.utils;


/**
 * This interface is used to mark all features that can be toggled on and off.
 */
public interface Toggleable {
    /**
     * Toggles the feature.
     */
    void toggle();

    /**
     * Runs when the feature is enabled.
     * <p>
     *     This method is called in the {@link #toggle()} method.
     * </p>
     *
     * @see #toggle()
     */
    default void onEnable() {};

    /**
     * Runs when the feature is disabled.
     * <p>
     *     This method is called in the {@link #toggle()} method.
     * </p>
     *
     * @see #toggle()
     */
    default void onDisable() {};
}
