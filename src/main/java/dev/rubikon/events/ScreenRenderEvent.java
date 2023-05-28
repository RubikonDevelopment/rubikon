package dev.rubikon.events;

import dev.rubikon.utils.Event;

/**
 * Represents the screen render event.
 * @param delta The time in seconds since the last frame.
 */
public record ScreenRenderEvent(float delta) implements Event {}
