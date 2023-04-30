package dev.rubikon.events;

import dev.rubikon.api.commons.Event;

public record ScreenRenderEvent(float delta) implements Event {}
