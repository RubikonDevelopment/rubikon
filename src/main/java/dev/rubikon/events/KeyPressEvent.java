package dev.rubikon.events;

import dev.rubikon.api.commons.Event;

public record KeyPressEvent(int key, int action) implements Event {}
