package dev.rubikon.events;

import dev.rubikon.api.commons.Event;

/**
 * @param delta The time in seconds since the last frame.
 */
public record ScreenRenderEvent(float delta) implements Event {}
