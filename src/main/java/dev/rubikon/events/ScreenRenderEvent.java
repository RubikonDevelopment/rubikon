package dev.rubikon.events;

import dev.rubikon.api.commons.Event;

public class ScreenRenderEvent implements Event {
    private final float delta;
    public ScreenRenderEvent(float delta) {
        this.delta = delta;
    }

    public float getDelta() {
        return delta;
    }
}
